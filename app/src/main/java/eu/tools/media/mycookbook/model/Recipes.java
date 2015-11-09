package eu.tools.media.mycookbook.model;

import java.util.ArrayList;
import java.util.UUID;

public class Recipes {

    ArrayList<Recipe> list;
    static private Ingredients ingredients;

    public Recipes(Connection connnect) {

        //UUID recipeId, String name, String author, ArrayList<UsedIngredient> ingredients
        UUID var = UUID.fromString("1A5921A0-872A-11E5-9316-A5F464696656");
        ArrayList<UsedIngredient> needs = new ArrayList<UsedIngredient>() ;
        UsedIngredient i1 = new UsedIngredient(ingredients.getIngregient("1"),150,"g");
        needs.add(i1);
        UsedIngredient i2 = new UsedIngredient(ingredients.getIngregient("2"),75,"g");
        needs.add(i2);
        UsedIngredient i3 = new UsedIngredient(ingredients.getIngregient("3"),5,"");
        needs.add(i3);
        UsedIngredient i4 = new UsedIngredient(ingredients.getIngregient("4"),200,"g");
        needs.add(i4);
        UsedIngredient i5 = new UsedIngredient(ingredients.getIngregient("5"),100,"g");
        needs.add(i5);

        Recipe Fondant = new Recipe(var, "Fondant au chocolat", "Béatrice", needs, "blalblalba");

        this.list.add(Fondant);




    }



//    TODO : get a recipe by ad Id in cache or on the server
//    public Recipe getRecipe (UUID id) {
//        if (list.contains(id)) {
//            list.get();
//        }
//    }

}
