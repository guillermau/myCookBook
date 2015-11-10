package eu.tools.media.mycookbook.model;

import android.util.Base64;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.UUID;

public class Recipe {

    Connection connection;
    String recipeId;
    String name;
    String author;
    // ArrayList<String> contributors; // TODO : Social pakage
    Base64 photo;
    String licence;
    String original_recipe;
    // String publication_rules; // TODO : Social pakage
    ArrayList<UsedIngredient> ingredients;
    String instructions;
    // ArrayList<Instruction> instructions; // TODO : MetaData Package

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Base64 getPhoto() {
        return photo;
    }

    public void setPhoto(Base64 photo) {
        this.photo = photo;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getOriginal_recipe() {
        return original_recipe;
    }

    public void setOriginal_recipe(String original_recipe) {
        this.original_recipe = original_recipe;
    }

    public ArrayList<UsedIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<UsedIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Recipe () {

    }

    public Recipe(Connection connection, String name, String author, ArrayList<UsedIngredient> ingredients, String instructions) {
        this.name = name;
        this.author = author;
        this.ingredients = ingredients;
        this.instructions =  instructions;
        this.licence = "MIT";
    }

    public Recipe(Connection connection, String recipeId) {
        this.connection = connection;
        this.recipeId = recipeId;

        switch (recipeId) {
            case "1" :
                this.name = "Fondant au chocolat";
                this.author = "Béatrice";
                this.instructions = "balabla";
                this.ingredients = new ArrayList<UsedIngredient>() ;
                UsedIngredient i1 = new UsedIngredient(new Ingredient(connection, "1"), 150, "g");
                this.ingredients.add(i1);
                UsedIngredient i2 = new UsedIngredient(new Ingredient(connection, "2"), 75, "g");
                this.ingredients.add(i2);
                UsedIngredient i3 = new UsedIngredient(new Ingredient(connection, "3"), 5, "");
                this.ingredients.add(i3);
                UsedIngredient i4 = new UsedIngredient(new Ingredient(connection, "4"), 200, "g");
                this.ingredients.add(i4);
                UsedIngredient i5 = new UsedIngredient(new Ingredient(connection, "5"), 100, "g");
                this.ingredients.add(i5);
                break;
            case "2" :
                this.name = "Oeufs au plat";
                this.author = "Un vieux barbu";
                this.instructions = "Casser les oeufs";
                this.ingredients = new ArrayList<UsedIngredient>();
                UsedIngredient i6 = new UsedIngredient(new Ingredient(connection, "3"), 5, "");
                this.ingredients.add(i6);
                break;
            case "3" :
                this.name = "Crepes";
                this.author = "La mere michel";
                this.instructions = "Casser les oeufs et mélanger. Ajouter la farine et le lait. Melanger.";
                this.ingredients = new ArrayList<UsedIngredient>();
                UsedIngredient i7 = new UsedIngredient(new Ingredient(connection, "3"), 3, "");
                this.ingredients.add(i7);
                UsedIngredient i8 = new UsedIngredient(new Ingredient(connection, "6"), 500, "mL");
                this.ingredients.add(i8);
                UsedIngredient i9 = new UsedIngredient(new Ingredient(connection, "2"), 250, "g");
                this.ingredients.add(i9);
                break;
            case "4" :
                this.name = "Bébé rose";
                this.author = "Boudha";
                this.instructions = "Versez le sirop au fond d'un verre, puis ajouter le lait";
                this.ingredients = new ArrayList<UsedIngredient>();
                UsedIngredient i10 = new UsedIngredient(new Ingredient(connection, "7"), 5, "mL");
                this.ingredients.add(i10);
                UsedIngredient i11 = new UsedIngredient(new Ingredient(connection, "6"), 20, "mL");
                this.ingredients.add(i11);
                break;
        }
    }
}
