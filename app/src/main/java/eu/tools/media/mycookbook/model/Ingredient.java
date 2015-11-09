package eu.tools.media.mycookbook.model;

import android.util.Base64;
import java.util.ArrayList;
import java.util.Date;

public class Ingredient {

    public String ingredientId;
    // public String category; TODO : MetadataPackage
    public String name;
    public Base64 image;
    public ArrayList<String> allergen;
    // public String state; TODO : MetadataPackage
    public String description;
    // public ArrayList<Date> season; TODO : MetadataPackage
    // public ArrayList<String> translation; TODO : Language Package

    public String getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(String ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Base64 getImage() {
        return image;
    }

    public void setImage(Base64 image) {
        this.image = image;
    }

    public ArrayList<String> getAllergen() {
        return allergen;
    }

    public void setAllergen(ArrayList<String> allergen) {
        this.allergen = allergen;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Ingredient(String name) {
        this.name = name;
    }

    public Ingredient(String ingredientId, String name, String description, ArrayList<Allergen> allergen) {
        this.ingredientId = ingredientId;
        this.name = name;
        this.description = description;
    }
}
