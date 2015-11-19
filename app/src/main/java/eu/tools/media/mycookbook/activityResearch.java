package eu.tools.media.mycookbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import eu.tools.media.mycookbook.model.Allergen;
import eu.tools.media.mycookbook.model.Connection;
import eu.tools.media.mycookbook.model.Recipe;
import eu.tools.media.mycookbook.model.UsedIngredient;
import eu.tools.media.mycookbook.model.User;

/**
 * Created by benedictefahrer on 19/11/2015.
 */
public class activityResearch extends AppCompatActivity {

    final String EXTRA_LOGIN = "user_login";
    final String EXTRA_PASSWORD = "user_password";
    final String EXTRA_RECIPE = "product";
    final String EXTRA_POSITION = "position";
    private ListView lView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research);
        Intent intent = getIntent();


        // Création d'un user
        Connection connection = new Connection("couchDB.Boudrat.eu","5984","mycookbook");
        User myUser = new User(connection,intent.getStringExtra(EXTRA_LOGIN),intent.getStringExtra(EXTRA_PASSWORD));

        ArrayList<Recipe> listRecette = myUser.getCookbook();
        final String EXTRA_LOGIN = "user_login";
        final String EXTRA_PASSWORD = "user_password";

        ArrayList<String> listNoms = new ArrayList<String>();
        for (int i = 0; i < listRecette.size(); ++i) {
            Recipe recette = listRecette.get(i);
            ArrayList<UsedIngredient> usedIngredientArrayList = recette.getIngredients();
            for(int index = 0; index < usedIngredientArrayList.size(); ++index)
            {
                UsedIngredient ingre = usedIngredientArrayList.get(index);
                Log.d("debug",ingre.getUsedIngredient().getName());
                ArrayList<Allergen> allergenList = ingre.getUsedIngredient().getAllergen();
                for(int j = 0; j < allergenList.size(); ++j)
                {
                    Log.d("debug", "test");
                    String allergen = allergenList.get(j).getName();
                    Log.d("debug",allergen);
                    listNoms.add(allergen);
                }
            }

        }

        // parcourt de la liste et suppression des doublons
        Set set = new HashSet() ;
        set.addAll(listNoms) ;
        ArrayList listAllergen = new ArrayList(set) ;


        //Création de l'adapter
        lView = (ListView) findViewById(R.id.listView);
        lView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, listAllergen));
        lView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        // listening to single list item on click
        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


            }
        });

    }
}
