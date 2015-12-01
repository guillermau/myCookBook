package eu.tools.media.mycookbook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.util.SparseBooleanArray;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import eu.tools.media.mycookbook.model.Allergen;
import eu.tools.media.mycookbook.model.Connection;
import eu.tools.media.mycookbook.model.Recipe;
import eu.tools.media.mycookbook.model.UsedIngredient;
import eu.tools.media.mycookbook.model.User;

public class activityResearch extends AppCompatActivity {

    static final String PREFS_NAME = "SavedLogin";
    static final String PREFS_PROFILE = "SavedProfile";
    static final String PREFS_DATABASE = "SavedDatabase";

    final String EXTRA_LOGIN = "user_login";
    final String EXTRA_PASSWORD = "user_password";
    final String EXTRA_RECIPE = "product";
    final String EXTRA_POSITION = "position";
    private ListView lView;

    // list of allergen checked in the list
    ArrayList<String> listIdAllergenSelected = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research);
        Intent intent = getIntent();

        final ArrayList<String> listNoms = new ArrayList<String>();
        final ArrayList<String> listId = new ArrayList<String>();
        lView = (ListView) findViewById(R.id.listView);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, listNoms);
        lView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        SharedPreferences profile = getSharedPreferences(PREFS_DATABASE, 0);
        String baseUrl = profile.getString("url", "false");
        String url = baseUrl+"_design/allergen/_view/byName";

        // Load queue for connection
        final RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.

        JsonObjectRequest passwordRequest = new JsonObjectRequest(Request.Method.GET, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray rows = response.getJSONArray("rows");

                            for (int i = 0; i < rows.length() ; i++) {
                                JSONObject allergen = rows.getJSONObject(i);
                                String allergenName = allergen.getString("key");
                                String allergenId = allergen.getString("id");
                                listNoms.add(allergenName);
                                listId.add(allergenId);
                                lView.setAdapter(adapter);
                            }
                        }
                        catch (JSONException exp) {
                            Log.e("Error", "Bad JSON", exp);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO handle error
            }
        });
        // Add the request to the RequestQueue.
        queue.add(passwordRequest);


        // listening to single list item on click
        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                SparseBooleanArray positions = lView.getCheckedItemPositions();
                // array containing the selected items : name of the allergen
                ArrayList<String> selectedItems = new ArrayList<String>();
                for (int i = 0; i < positions.size(); i++) {
                    // Item position in adapter
                    int positionTest = positions.keyAt(i);
                    Log.d("debug", "pos "+positionTest);
                   if (positions.valueAt(i))
                   selectedItems.add(adapter.getItem(positionTest));
                    Log.d("debug",listId.get(positionTest));
                    listIdAllergenSelected.add(listId.get(positionTest));
                   }



                // Send a list of allergen

                // Bind ingredient

                // Get recipes

                // Send recipes id to the new activity
            }
        });

    }
}
