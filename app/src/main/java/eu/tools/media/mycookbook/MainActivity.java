package eu.tools.media.mycookbook;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "SavedLogin";
    public static final String PREFS_PROFILE = "SavedProfile";
    public static final String PREFS_DATABASE = "SavedDatabase";

    private EditText m_login = null;
    private EditText m_password = null;
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

        // get login and password
        m_login = (EditText) findViewById(R.id.username);
        m_password = (EditText) findViewById(R.id.password);
        m_signInButton = (Button) findViewById(R.id.buttonLogin);
        m_passwordButton = (Button) findViewById(R.id.buttonPassword);

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
