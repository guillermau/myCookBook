package eu.tools.media.mycookbook;

/**
 * Created by marion on 19/11/2015.
 */
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

import eu.tools.media.mycookbook.R;
import eu.tools.media.mycookbook.model.Connection;
import eu.tools.media.mycookbook.model.Recipe;
import eu.tools.media.mycookbook.model.User;



public class activityUser extends AppCompatActivity {
    final String EXTRA_LOGIN = "user_login";
    final String EXTRA_PASSWORD = "user_password";
    TextView m_userName = null;
    TextView m_emailAddress = null;
    TextView m_profileUser = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Intent intent = getIntent();

        // Création d'un user
        Connection connection = new Connection("couchDB.Boudrat.eu","5984","mycookbook");
        User myUser = new User(connection,intent.getStringExtra(EXTRA_LOGIN),intent.getStringExtra(EXTRA_PASSWORD));

        final String username = myUser.getUsername();
        final String pass = myUser.getPassword();
        String mail = myUser.getEmailAddress();
            /*Log.d ("debug", username);
            Log.d("debug",pass );
            Log.d("debug", mail);*/

        final String EXTRA_LOGIN = "user_login";
        final String EXTRA_PASSWORD = "user_password";


        // Profile Of FirstName FamilyName
        m_profileUser = (TextView) findViewById(R.id.pageLogin);
        String firstName = myUser.getFirstName();
        String familyName = myUser.getFamilyName();
        String name_profile = "Profil de ";
        name_profile += firstName;
        name_profile += " ";
        name_profile += familyName;
        m_profileUser.setText(name_profile);

        // Display user name
        m_userName = (TextView) findViewById(R.id.textViewNom);
        String nom_user = " ";
        nom_user += username;
        m_userName.setText(nom_user);

        // Display Email Address
        m_emailAddress = (TextView) findViewById(R.id.textViewMail);
        String email = myUser.getEmailAddress();
        String user_address = "";
        user_address += email;
        m_emailAddress.setText(user_address);

    }
}