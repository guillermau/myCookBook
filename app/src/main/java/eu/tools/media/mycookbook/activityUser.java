package eu.tools.media.mycookbook;

/**
 * Created by marion on 19/11/2015.
 */
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;import android.support.v7.widget.Toolbar;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class activityUser extends AppCompatActivity {
    public static final String PREFS_NAME = "SavedLogin";

    final String EXTRA_LOGIN = "user_login";
    final String EXTRA_PASSWORD = "user_password";

    TextView m_userName = null;
    TextView m_emailAddress = null;
    TextView m_profileUser = null;
    TextView m_textViewRecette = null;
    final String LOGIN = "login";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Intent intent = getIntent();

        // TODO Intend ou Saved ?
        SharedPreferences profile = getSharedPreferences(PREFS_NAME, 0);
        String savedUsername = profile.getString("username", "false");
        String savedPassword = profile.getString("password", "false");

        Log.d("debug",intent.getStringExtra(LOGIN));
        final String username = intent.getStringExtra(LOGIN);

        final String EXTRA_LOGIN = "user_login"; // TODO Usefull ?
        final String EXTRA_PASSWORD = "user_password"; // TODO Usefull ?

        m_profileUser = (TextView) findViewById(R.id.pageLogin);
        m_userName = (TextView) findViewById(R.id.textViewNom);
        m_emailAddress = (TextView) findViewById(R.id.textViewMail);
        m_textViewRecette = (TextView) findViewById(R.id.textViewRecette);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://couchdb.bourdat.eu:5984/mycookbook/"+savedUsername;

        // Request a string response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            m_emailAddress.setText(response.getString("emailAddress"));
                            m_profileUser.setText("Profil de " + response.getString("firstName")
                                    + " " + response.getString("familyName"));
                            m_userName.setText(response.getString("username"));
                            JSONArray CookBook = response.getJSONArray("cookbook");
                            m_textViewRecette.setText(new Integer(CookBook.length()).toString());

                        } catch (JSONException exp) {
                            Log.e("Error","Bad JSON", exp);
                        }
                        // Display the first 500 characters of the response string.

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                m_emailAddress.setText("That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}