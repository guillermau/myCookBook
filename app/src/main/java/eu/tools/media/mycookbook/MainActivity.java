package eu.tools.media.mycookbook;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

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

        final String EXTRA_LOGIN = "user_login";
        final String EXTRA_PASSWORD = "user_password";

        m_signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // new activity: Your cook book
                Intent intent = new Intent(MainActivity.this, activityProfile.class);
                intent.putExtra(EXTRA_LOGIN,m_login.getText().toString());
                intent.putExtra(EXTRA_PASSWORD,m_password.getText().toString());
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
