package eu.tools.media.mycookbook;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "SavedLogin";
    public static final String PREFS_PROFILE = "SavedProfile";
    public static final String PREFS_DATABASE = "SavedDatabase";

    private EditText m_login = null;
    private EditText m_password = null;
    private EditText m_errorMessage= null;
    private Button m_signInButton = null;
    private Button m_passwordButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set database prefs
        SharedPreferences settings = getSharedPreferences(PREFS_DATABASE, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("hostname", "couchdb.bourdat.eu");
        editor.putString("database", "mycookbook");
        editor.putString("port", "5984");
        editor.putString("user", "");
        editor.putString("password","");
        editor.putString("url", "http://couchdb.bourdat.eu:5984/mycookbook/");

        editor.commit(); // Commit the edits!

        // Load queue for connection
        final RequestQueue queue = Volley.newRequestQueue(this);

        // get login and password
        m_login = (EditText) findViewById(R.id.username);
        m_password = (EditText) findViewById(R.id.password);
        m_signInButton = (Button) findViewById(R.id.buttonLogin);
        m_passwordButton = (Button) findViewById(R.id.buttonPassword);
        m_errorMessage = (EditText) findViewById(R.id.errorMessage);

        // Sznd saved name on interface
        SharedPreferences profile = getSharedPreferences(PREFS_NAME, 0);
        String savedUsername = profile.getString("username", "false");
        if (savedUsername!= "false") {
            m_login.setText(savedUsername);
        }
        String savedPassword = profile.getString("password", "false");
        if (savedUsername!= "false") {
            m_password.setText(savedPassword);
        }

        m_signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences profile = getSharedPreferences(PREFS_DATABASE, 0);
                String baseUrl = profile.getString("url", "false");
                String url = baseUrl+"_design/user/_view/byUserName?key=\""+m_login.getText().toString()+"\"";

                // Request a string response from the provided URL.
                JsonObjectRequest passwordRequest = new JsonObjectRequest(Request.Method.GET, url,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray rows = response.getJSONArray("rows");
                                    JSONObject value = rows.getJSONObject(0).getJSONObject("value");

                                    if (value.getString("password").equals(m_password.getText().toString())) {
                                        // good password
                                        m_errorMessage.setVisibility(View.INVISIBLE);

                                        // We need an Editor object to make preference changes.
                                        // All objects are from android.context.Context
                                        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                                        SharedPreferences.Editor editor = settings.edit();
                                        editor.putString ("username", m_login.getText().toString());
                                        editor.putString ("password", m_password.getText().toString());

                                        editor.commit(); // Commit the edits!



                                        // new activity: Your cook book
                                        Intent intent = new Intent(MainActivity.this, activityProfileTest.class);
                                        // intent.putExtra(EXTRA_LOGIN, m_login.getText().toString());
                                        startActivity(intent);
                                    }
                                    else {
                                        // bad password
                                        m_errorMessage.setVisibility(View.VISIBLE);
                                    }

                                } catch (JSONException exp) {
                                    Log.e("Error", "Bad JSON", exp);
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                // Add the request to the RequestQueue.
                queue.add(passwordRequest);

            }
        });

        m_passwordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // new activity: password forgotten
                Intent intent = new Intent(MainActivity.this, activityForgotPassword.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onBackPressed()
    {
        moveTaskToBack(true);
    }
}
