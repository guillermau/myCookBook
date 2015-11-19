package eu.tools.media.mycookbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import eu.tools.media.mycookbook.model.Connection;
import eu.tools.media.mycookbook.model.Ingredient;
import eu.tools.media.mycookbook.model.Recipe;
import eu.tools.media.mycookbook.model.UsedIngredient;
import eu.tools.media.mycookbook.model.User;

/**
 * Created by benedictefahrer on 10/11/2015.
 */
public class activityEdit extends AppCompatActivity  {
    EditText m_editText = null;
    FloatingActionButton m_saveButton;
    final String EXTRA_LOGIN = "user_login";
    final String EXTRA_PASSWORD = "user_password";
    final String EXTRA_POSITION = "position";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editrecipe);
        Intent intent = getIntent();

        m_editText = (EditText) findViewById(R.id.editText);
        m_saveButton = (FloatingActionButton) findViewById(R.id.fab);

        // Création d'un user
        Connection connection = new Connection("couchDB.Boudrat.eu","5984","mycookbook");
        final User myUser = new User(connection,intent.getStringExtra(EXTRA_LOGIN),intent.getStringExtra(EXTRA_PASSWORD));

        // get the list of recipe
        final ArrayList<Recipe> listRecette = myUser.getCookbook();

        // get the recipe we want ti visualize
        final int position = intent.getIntExtra(EXTRA_POSITION, 0);
        Log.d("debug", "position" + position);

        final Recipe recette = listRecette.get(position);
        String instructions = "\n";
        instructions += recette.getInstructions();
        m_editText.setText(instructions,EditText.BufferType.EDITABLE);

        m_saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save the text
                recette.setInstructions(m_editText.getText().toString());
                listRecette.set(position, recette);
                Log.d("debug", recette.getInstructions());


            }
        });


    }
}
