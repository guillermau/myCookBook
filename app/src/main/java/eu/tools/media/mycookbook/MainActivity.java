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

    private EditText m_login = null;
    private EditText m_password = null;
    private Button m_signInButton = null;
    private Button m_passwordButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // get login and password
        m_login = (EditText) findViewById(R.id.username);
        m_password = (EditText) findViewById(R.id.password);
        m_signInButton = (Button) findViewById(R.id.buttonLogin);
        m_passwordButton = (Button) findViewById(R.id.buttonPassword);

        SharedPreferences profile = getSharedPreferences(PREFS_NAME, 0);
        String savedUsername = profile.getString("username", "false");
        if (savedUsername!= "false") {
            m_login.setText(savedUsername);
        }
        String savedPassword = profile.getString("password", "false");
        if (savedUsername!= "false") {
            m_password.setText(savedPassword);
        }

        final String EXTRA_LOGIN = "user_login";
        final String EXTRA_PASSWORD = "user_password";

        m_signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // We need an Editor object to make preference changes.
                // All objects are from android.context.Context
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString ("username", m_login.getText().toString());
                editor.putString ("password", m_password.getText().toString());

                // Commit the edits!
                editor.commit();

                // new activity: Your cook book
                Intent intent = new Intent(MainActivity.this, activityProfileTest.class);
                intent.putExtra(EXTRA_LOGIN, m_login.getText().toString());
                intent.putExtra(EXTRA_PASSWORD, m_password.getText().toString());
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
