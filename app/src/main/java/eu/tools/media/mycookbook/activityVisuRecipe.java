package eu.tools.media.mycookbook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class activityVisuRecipe extends AppCompatActivity{

    static final String PREFS_NAME = "SavedLogin";
    static final String PREFS_PROFILE = "SavedProfile";
    static final String PREFS_DATABASE = "SavedDatabase";

    final String EXTRA_RECIPE = "idRecipe";

    RequestQueue queue;
    String baseUrl;

    TextView m_ingredient = null;
    TextView m_instructions = null;
    TextView m_nameRecette = null;
    TextView m_tmpPrep = null;
    TextView m_tmpCook = null;
    TextView m_tmpTotal = null;

    private FloatingActionButton m_editButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visurecipe);
        Intent intent = getIntent();

        SharedPreferences profile = getSharedPreferences(PREFS_DATABASE, 0);
        baseUrl = profile.getString("url", "false");

        m_ingredient = (TextView) findViewById(R.id.ingredientsList);
        m_instructions = (TextView) findViewById(R.id.instructionsList);
        m_nameRecette = (TextView) findViewById(R.id.nameRecipe);
        m_editButton = (FloatingActionButton) findViewById(R.id.fab);
        m_tmpPrep = (TextView) findViewById(R.id.tmpPrep);
        m_tmpCook = (TextView) findViewById(R.id.tmpCook);
        m_tmpTotal =  (TextView) findViewById(R.id.tmpTotal);

        // get the recipe we want to visualize
        final String idRecipe = intent.getStringExtra(EXTRA_RECIPE);

        // Instantiate the RequestQueue.
        queue = Volley.newRequestQueue(this);

        m_editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // new activity: Edit the recipe
                Intent intent = new Intent(activityVisuRecipe.this, activityEditRecipe.class);
                intent.putExtra("idRecipe", idRecipe);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        // get the recipe we want to visualize
        Intent intent = getIntent();
        final String idRecipe = intent.getStringExtra(EXTRA_RECIPE);

        JsonObjectRequest recipeRequest = new JsonObjectRequest(Request.Method.GET, baseUrl+idRecipe,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject recipe) {
                        try {
                            m_nameRecette.setText(recipe.getString("name"));
                            m_instructions.setText(recipe.getString("instructions"));
                            Integer tempsCuisson = new Integer(recipe.getInt("cookingTime"));
                            Integer tempsPreparation = new Integer(recipe.getInt("preparationTime"));
                            Integer tempsTotal = tempsCuisson + tempsPreparation;
                            m_tmpPrep.setText(tempsPreparation.toString());
                            m_tmpCook.setText(tempsCuisson.toString());
                            m_tmpTotal.setText(tempsTotal.toString());
                            m_ingredient.setText("");

                            // TODO ListView
                            JSONArray ingredients = recipe.getJSONArray("ingredients");
                            for(int i = 0; i < ingredients.length(); i++) {
                                String idIngredient = ingredients.getJSONObject(i).getString("ingredient");
                                final Integer quantity = ingredients.getJSONObject(i).getInt("quantity");
                                final String unit = ingredients.getJSONObject(i).getString("unit");

                                String urlIngredient = baseUrl+idIngredient;
                                JsonObjectRequest ingredientRequest = new JsonObjectRequest(Request.Method.GET, urlIngredient, new Response.Listener<JSONObject>(){
                                    @Override
                                    public void onResponse(JSONObject ingredient) {
                                        try {
                                            String ingredientList = m_ingredient.getText().toString();
                                            ingredientList += "- "+quantity+" "+unit+" "+ingredient.getString("name")+"\n";
                                            m_ingredient.setText(ingredientList);
                                        }
                                        catch (JSONException err) {

                                        }

                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        // TODO Handle error
                                    }
                                });

                                queue.add(ingredientRequest);

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
        queue.add(recipeRequest);
    }
}
