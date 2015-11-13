package eu.tools.media.mycookbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
public class activityVisuRecipe extends AppCompatActivity{
    final String EXTRA_LOGIN = "user_login";
    final String EXTRA_PASSWORD = "user_password";
    final String EXTRA_RECIPE = "product";
    final String EXTRA_POSITION = "position";
    TextView m_ingredient = null;
    TextView m_instructions = null;
    TextView m_nameRecette = null;
    TextView m_temps = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visurecipe);
        Intent intent = getIntent();

        m_ingredient = (TextView) findViewById(R.id.ingredientsList);
        m_instructions = (TextView) findViewById(R.id.instructionsList);
        m_nameRecette = (TextView) findViewById(R.id.nameRecipe);
        m_temps = (TextView) findViewById(R.id.temps);

        // Création d'un user
        Connection connection = new Connection("couchDB.Boudrat.eu","5984","mycookbook");
        User myUser = new User(connection,intent.getStringExtra(EXTRA_LOGIN),intent.getStringExtra(EXTRA_PASSWORD));

        // get the list of recipe
        ArrayList<Recipe> listRecette = myUser.getCookbook();

        // get the recipe we want ti visualize
        String recipe = intent.getStringExtra(EXTRA_RECIPE);
        int position = intent.getIntExtra(EXTRA_POSITION, 0);
        Log.d("debug", recipe);
        Log.d("debug", "position" + position);

        Recipe recette = listRecette.get(position);
        String instructions = recette.getInstructions();
        m_instructions.setText(instructions);

        String nameRecette = recette.getName();
        m_nameRecette.setText(nameRecette);

        int tempsCuisson = recette.getTempsCuisson();
        Log.d("debug", " " + tempsCuisson);
        int tempsPreparation = recette.getTempsPreparation();
        Log.d("debug", " " + tempsPreparation);

        String tempsTotal = " temps de cuisson : ";
        tempsTotal += tempsCuisson;
        tempsTotal +=" minutes";
        tempsTotal += "\n";
        tempsTotal+= " temps de préparation: ";
        tempsTotal += tempsPreparation;
        tempsTotal +=" minutes";
        m_temps.setText(tempsTotal);




        ArrayList<UsedIngredient> ingredients = recette.getIngredients();

        String nom_total = "\n";
        for(int i = 0; i < ingredients.size(); ++i)
        {
            UsedIngredient ingredient = ingredients.get(i);
            Ingredient ingre = ingredient.getUsedIngredient();
            int quantite = ingredient.getQuantity();
            String unite = ingredient.getUnit();
            String nom = ingre.getName();
            nom_total += "  - ";
            nom_total += quantite;
            nom_total += " ";
            nom_total += unite;
            nom_total += " ";
            nom_total += nom;
            nom_total += "\n";
        }

        m_ingredient.setText(nom_total);

    }
}
