package eu.tools.media.mycookbook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import java.util.HashMap;

import eu.tools.media.mycookbook.model.Connection;
import eu.tools.media.mycookbook.model.Ingredient;
import eu.tools.media.mycookbook.model.Recipe;
import eu.tools.media.mycookbook.model.UsedIngredient;
import eu.tools.media.mycookbook.model.User;

public class activityEditRecipe extends AppCompatActivity  {

    static final String PREFS_NAME = "SavedLogin";
    static final String PREFS_PROFILE = "SavedProfile";
    static final String PREFS_DATABASE = "SavedDatabase";

    final String EXTRA_RECIPEID = "idRecipe";

    EditText m_editText = null;
    FloatingActionButton m_saveButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editrecipe);
        Intent intent = getIntent();

        final String IdRecipe = intent.getStringExtra(EXTRA_RECIPEID);

        SharedPreferences profile = getSharedPreferences(PREFS_DATABASE, 0);
        final String baseUrl = profile.getString("url", "false");

        // Instantiate the RequestQueue.
        final RequestQueue queue = Volley.newRequestQueue(this);
        final String urlRecipe = baseUrl+IdRecipe;

        m_editText = (EditText) findViewById(R.id.editText);
        m_saveButton = (FloatingActionButton) findViewById(R.id.fab);

        JsonObjectRequest recipeRequest = new JsonObjectRequest(Request.Method.GET, urlRecipe,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject recipe) {
                        try {
                            m_editText.setText(recipe.getString("instructions"), EditText.BufferType.EDITABLE);
                            Log.i("instructions", recipe.getString("instructions"));
                        } catch (JSONException exp) {
                            Log.e("Error","Bad JSON", exp);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error","Bad JSON", error);
            }
        });
        // Add the request to the RequestQueue.
        queue.add(recipeRequest);


        m_saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save the text
                JsonObjectRequest recipeRequest = new JsonObjectRequest(Request.Method.GET, urlRecipe,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject recipe) {

                                final JSONObject transRecipe = recipe;

                                JsonObjectRequest udapteRecipeRequest = new JsonObjectRequest(Request.Method.PUT, urlRecipe,
                                        new Response.Listener<JSONObject>() {
                                            @Override
                                            public void onResponse(JSONObject responseRequest) {
                                                // Launching new Activity on selecting single List Item
                                                Intent i = new Intent(getApplicationContext(), activityVisuRecipe.class);
                                                // sending data to new activity
                                                i.putExtra("idRecipe", IdRecipe);
                                                startActivity(i);

                                            }
                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        // TODO Handle error
                                    }
                                }) {

                                    @Override
                                    public HashMap<String, String> getParams() {
                                        HashMap<String, String> params = new HashMap<String, String>();
                                        params.put("Content-Type", "application/json; charset=utf-8");

                                        return params;
                                    }

                                    // Returns the raw POST or PUT body to be sent.
                                    @Override
                                    public byte[] getBody() {
                                        try {
                                            JSONObject toSend = transRecipe;
                                            toSend.put("instructions", m_editText.getText().toString());                                            ;
                                            return toSend.toString().getBytes("UTF-8");
                                        }
                                        catch (UnsupportedEncodingException exep) {
                                            return null;
                                        }
                                        catch (JSONException exep) {
                                            return null;
                                        }
                                    }

                                };
                                // Add the request to the RequestQueue.
                                queue.add(udapteRecipeRequest);

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("EditRecipe", "Save Error" , error);
                    }
                });
                // Add the request to the RequestQueue.
                queue.add(recipeRequest);

            }
        });


    }
}
