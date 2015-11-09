package eu.tools.media.mycookbook.model;

import android.util.Base64;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.UUID;

public class Recipe {

    UUID recipeId;
    String name;
    String author;
    // ArrayList<String> contributors; // TODO : Social pakage
    Base64 photo;
    String licence;
    UUID original_recipe;
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

    public UUID getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(UUID recipeId) {
        this.recipeId = recipeId;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public UUID getOriginal_recipe() {
        return original_recipe;
    }

    public void setOriginal_recipe(UUID original_recipe) {
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

    public Recipe(UUID recipeId, String name, String author, ArrayList<UsedIngredient> ingredients, String instructions) {
        this.recipeId = recipeId;
        this.name = name;
        this.author = author;
        this.ingredients = ingredients;
        this.instructions =  instructions;
        this.licence = "MIT";
    }
}
