package eu.tools.media.mycookbook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
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

    private ListView lView;
    private ListView m_listView2;
    private FloatingActionButton m_researchButton;

    // list of allergen checked in the list
    ArrayList<String> listIdAllergenSelected = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research);
        Intent intent = getIntent();

        final ArrayList<String> listRecipe = new ArrayList<>();
        final ArrayList<String> listIdRecipe = new ArrayList<>();

        final ArrayList<String> listNoms = new ArrayList<String>();
        final ArrayList<String> listId = new ArrayList<String>();
        lView = (ListView) findViewById(R.id.listView);
        m_listView2 = (ListView) findViewById(R.id.listView2);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, listNoms);
        final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listRecipe);
        lView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        m_researchButton = (FloatingActionButton) findViewById(R.id.resarch_button);

        SharedPreferences profile = getSharedPreferences(PREFS_DATABASE, 0);
        final String baseUrl = profile.getString("url", "false");
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
                    Log.d("debug", "pos " + positionTest);
                    if (positions.valueAt(i))
                        selectedItems.add(adapter.getItem(positionTest));
                    Log.d("debug", listId.get(positionTest));
                    listIdAllergenSelected.add(listId.get(positionTest));
                }
            }
        });

        // listening to single list item on click
        m_listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // selected item
                String idRecipe = listIdRecipe.get(position);

                // Launching new Activity on selecting single List Item
                Intent i = new Intent(getApplicationContext(), activityVisuRecipe.class);
                // sending data to new activity
                i.putExtra("idRecipe", idRecipe);
                startActivity(i);

            }
        });

        m_researchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listRecipe.clear();
                listIdRecipe.clear();

                // Send a list of allergen

                String url = baseUrl + "_design/recipe/_view/byIngredient";

                JsonObjectRequest recipeRequest = new JsonObjectRequest(Request.Method.GET, url,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray rows = response.getJSONArray("rows");

                                    for (int i = 0; i < rows.length(); i++) {
                                        JSONObject recipe = rows.getJSONObject(i);
                                        final String idRecipe = recipe.getString("id");
                                        final String nameRecipe = recipe.getString("key");
                                        JSONArray value = recipe.getJSONArray("value");
                                        listRecipe.add(nameRecipe);
                                        listIdRecipe.add(idRecipe);

                                        Log.d("Ananalysing recette " + nameRecipe, "En cours");

                                        for (int j = 0; j < value.length(); j++) {

                                            final String ingredientId = value.getString(j);
                                            Log.d("Ananalysing recette " + nameRecipe, ": " + ingredientId + " >> En cours");
                                            String urlIngredient = baseUrl + ingredientId;

                                            JsonObjectRequest ingredientRequest = new JsonObjectRequest(Request.Method.GET, urlIngredient,
                                                    new Response.Listener<JSONObject>() {
                                                        @Override
                                                        public void onResponse(JSONObject response) {
                                                            try {
                                                                //Log.d("ingredientRequest", response.getString("name")+" in "+nameRecipe);
                                                                JSONArray allergens = response.getJSONArray("allergen");

                                                                for (int k = 0; k < allergens.length(); k++) {
                                                                    for (int l = 0; l < listIdAllergenSelected.size(); l++) {
                                                                        //Log.d("Ananalysing recette "+nameRecipe,": "+ingredientId+" >> " + allergens.getString(k)+" >> En cours");
                                                                        if (allergens.getString(k).equals(listIdAllergenSelected.get(l))) {
                                                                            Log.d(listIdAllergenSelected.get(l) + " detected", "removing " + nameRecipe);
                                                                            listRecipe.remove(nameRecipe);
                                                                            listIdRecipe.remove(idRecipe);
                                                                            //Log.i("JSON : ", "removing " + nameRecipe);
                                                                            m_listView2.setAdapter(adapter2);
                                                                        }
                                                                    }
                                                                    Log.i("List R", listRecipe.toString());
                                                                }
                                                            } catch (JSONException exp) {
                                                                Log.e("Error", "Bad JSON in ingredientRequest", exp);
                                                            }
                                                        }
                                                    }, new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    // TODO handle error
                                                }
                                            });

                                            // Add the request to the RequestQueue.
                                            queue.add(ingredientRequest);
                                        }

                                        Log.d("Ananalysing recette " + nameRecipe, "Done");

                                        // lView.setAdapter(adapter);
                                    }

                                    Log.i("List R", listRecipe.toString());
                                    m_listView2.setAdapter(adapter2);
                                } catch (JSONException exp) {
                                    Log.e("Error", "Bad JSON in recipeRequest", exp);
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO handle error
                    }
                });
                // Add the request to the RequestQueue.
                queue.add(recipeRequest);
            }
        });

    }
}
