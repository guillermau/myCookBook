package eu.tools.media.mycookbook;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.util.Log;

import com.android.volley.*;
import com.android.volley.toolbox.*;
import org.json.*;

public class activityProfile extends AppCompatActivity {

    public static final String PREFS_NAME = "SavedLogin";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_profile);

            //Récupération du ListView présent dans notre IHM
            final ListView list1 = (ListView)findViewById(R.id.listViewUser);

            //Création de l'adapter
            final ArrayList<String> listNoms = new ArrayList<String>();
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listNoms);

            // TODO Intend ou Saved ?
            SharedPreferences profile = getSharedPreferences(PREFS_NAME, 0);
            String savedUsername = profile.getString("username", "false");
            String savedPassword = profile.getString("password", "false");


            // Instantiate the RequestQueue.
            final RequestQueue queue = Volley.newRequestQueue(this);
            final String baseUrl ="http://couchdb.bourdat.eu:5984/mycookbook/";
            final String userUrl = baseUrl+"/"+savedUsername;

            // Request a string response from the provided URL.
            Log.i("Info","Quering "+userUrl);
            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, userUrl,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray CookBook = response.getJSONArray("cookbook");
                                for (int i = 0; i < CookBook.length(); ++i) {
                                    String id = CookBook.getString(i);
                                    String idUrl = baseUrl + "/" + id;

                                    Log.i("Info","Quering "+idUrl);
                                    JsonObjectRequest recipeRequest = new JsonObjectRequest(Request.Method.GET, idUrl,
                                            new Response.Listener<JSONObject>() {
                                                @Override
                                                public void onResponse(JSONObject recipe) {
                                                    try {
                                                        listNoms.add(recipe.getString("name"));
                                                        list1.setAdapter(adapter);
                                                    } catch (JSONException exp) {
                                                        Log.e("Error","Bad JSON", exp);
                                                    }
                                                    // Display the first 500 characters of the response string.

                                                }
                                            }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            // TODO Handle error
                                        }
                                    });
                                    // Add the request to the RequestQueue.
                                    queue.add(recipeRequest);
                                }

                            } catch (JSONException exp) {
                                Log.e("Error","Bad JSON", exp);
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // TODO Handle error
                }
            });

            // Add the request to the RequestQueue.
            queue.add(stringRequest);

            // listening to single list item on click
            list1.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    // selected item
                    String product = ((TextView) view).getText().toString();

                    // Launching new Activity on selecting single List Item
                    Intent i = new Intent(getApplicationContext(), activityVisuRecipe.class);
                    // sending data to new activity
                    i.putExtra("product", product);
                    i.putExtra("position", position);
                    //Log.d("debug", "position "+position);
                    startActivity(i);

                }
            });

        }
}
