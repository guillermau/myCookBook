package eu.tools.media.cookbook;

/**
 * Created by benedictefahrer on 05/11/2015.
 */
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;

public class activityProfile extends AppCompatActivity {
    final String EXTRA_LOGIN = "user_login";
    final String EXTRA_PASSWORD = "user_password";
    private TextView m_login = null;
    private TextView m_password = null;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_profile);
            Intent intent = getIntent();


            // Spinner element
            Spinner spinner = (Spinner) findViewById(R.id.spinnerRecipe);

            // Spinner click listener
            //spinner.setOnItemSelectedListener(this);

            // Spinner Drop down elements
            List<String> recipes = new ArrayList<String>();
            recipes.add("Lasagne");
            recipes.add("Chili");
            recipes.add("TEst");

            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, recipes);

            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);


            m_login = (TextView) findViewById(R.id.loginTest);
            //m_password = (TextView) findViewById(R.id.passwordTest);

                m_login.setText(intent.getStringExtra(EXTRA_LOGIN));
                //m_password.setText(intent.getStringExtra(EXTRA_PASSWORD));

        }
}
