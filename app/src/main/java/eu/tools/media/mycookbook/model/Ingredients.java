package eu.tools.media.mycookbook.model;

import java.util.ArrayList;

public class Ingredients {

    public ArrayList<Ingredient> list;

    public Ingredients() {
        this.list.add(new Ingredient("1","Chocolat","C'est de la barre", null));
        this.list.add(new Ingredient("2","Farine de blé","C'est de la barre", null));
        this.list.add(new Ingredient("3","Oeuf","C'est de la barre", null));
        this.list.add(new Ingredient("4","Beurre","C'est de la barre", null));
        this.list.add(new Ingredient("5","Sucre","C'est de la barre", null));
    }

    public Ingredient getIngregient(String IngredientID) {

        for (int i = 0; i < this.list.size() ; i++) {
            if (this.list.get(i).getIngredientId().equals(IngredientID)) {
                return this.list.get(i);
            }
        }

        return null;
    }


}
