package eu.tools.media.mycookbook;

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

import eu.tools.media.mycookbook.model.Connection;
import eu.tools.media.mycookbook.model.Recipe;
import eu.tools.media.mycookbook.model.User;



public class activityProfile extends AppCompatActivity {
    final String EXTRA_LOGIN = "user_login";
    final String EXTRA_PASSWORD = "user_password";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_profile);
            Intent intent = getIntent();

            // Création d'un user
            Connection connection = new Connection("couchDB.Boudrat.eu","5984","mycookbook");
            User myUser = new User(connection,intent.getStringExtra(EXTRA_LOGIN),intent.getStringExtra(EXTRA_PASSWORD));
            List<String> list = new ArrayList<String>();
            list.add("list 1");
            list.add("list 2");
            list.add("list 3");

            String username = myUser.getUsername();
            String pass = myUser.getPassword();
            String mail = myUser.getEmailAddress();
            Log.d ("debug", username);
            Log.d("debug",pass );
            Log.d("debug",mail );
            ArrayList<Recipe> listRecette = myUser.getCookbook();
            Recipe recette = listRecette.get(0);
            String nom  = recette.getName();
            Log.d("debug",nom);

            //Création de l'adapter
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

            //Récupération du ListView présent dans notre IHM
            ListView list1 = (ListView)findViewById(R.id.listViewUser);

            //On passe nos données au composant ListView
            list1.setAdapter(adapter);



            //m_login = (TextView) findViewById(R.id.loginTest);
            //m_password = (TextView) findViewById(R.id.passwordTest);

                //m_login.setText(intent.getStringExtra(EXTRA_LOGIN));
                //m_password.setText(intent.getStringExtra(EXTRA_PASSWORD));

        }
}
