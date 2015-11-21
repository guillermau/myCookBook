package eu.tools.media.mycookbook.model;

import android.util.Base64;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Ingredient {

    private Connection connection;
    private String ingredientId;
    // public String category; TODO : MetadataPackage
    private String name;
    private Base64 image;
    private ArrayList<Allergen> allergen;
    // public String state; TODO : MetadataPackage
    private String description;
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

    public ArrayList<Allergen> getAllergen() {
        return allergen;
    }

    public void setAllergen(ArrayList<Allergen> allergen) {
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

    public Ingredient(Connection connection, String ingredientId) {
        this.ingredientId = ingredientId;
        this.connection = connection;

        switch (ingredientId) {
            case "1":
                this.name = "Chocolat";
                this.description = "C'est de la barre";
                this.allergen = new ArrayList<Allergen>();
                UUID allergenUid = UUID.randomUUID();;
                Allergen cacao = new Allergen(allergenUid,"cacao");
                this.allergen.add(cacao);

                break;
            case "2":
                this.name = "Farine de blé";
                this.description = "C'est de la barre";
                this.allergen = new ArrayList<Allergen>();
                UUID allergenUid1 = UUID.randomUUID();;
                Allergen glutene = new Allergen(allergenUid1,"glutene");
                this.allergen.add(glutene);
                break;
            case "3":
                this.name = "Oeufs";
                this.description = "C'est de la barre";
                this.allergen = new ArrayList<Allergen>();
                break;
            case "4":
                this.name = "Beurre";
                this.description = "C'est de la barre";
                this.allergen = new ArrayList<Allergen>();
                break;
            case "6":
                this.name = "Lait";
                this.description = "C'est de la barre";
                this.allergen = new ArrayList<Allergen>();
                UUID allergenUid2 = UUID.randomUUID();;
                Allergen lactose = new Allergen(allergenUid2,"lactose");
                this.allergen.add(lactose);
                break;
            case "7":
                this.name = "Sirop de fraise";
                this.description = "C'est de la barre";
                this.allergen = new ArrayList<Allergen>();
                break;
            default:
                this.ingredientId = "5";
                this.name = "Sucre";
                this.description = "C'est de la barre";
                this.allergen = new ArrayList<Allergen>();
                break;
        }
    }

    public Ingredient(String ingredientId, String name, String description, ArrayList<Allergen> allergen) {
        this.ingredientId = ingredientId;
        this.name = name;
        this.description = description;
    }
}
