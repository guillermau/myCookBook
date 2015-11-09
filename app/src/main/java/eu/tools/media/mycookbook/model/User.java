package eu.tools.media.mycookbook.model;

import java.util.ArrayList;
import java.util.UUID;

public class User {

    private Connection database;
    private UUID databaseId = null;
    private String username = null;
    private String familyName = null;
    private String firstName = null;
    private String emailAddress = null;
    private String password = null;
    private ArrayList<Recipe> cookbook;
    private ArrayList<String> allergies;
    private ArrayList<String> forbiddenIngredient = null;

    // private ArrayList<String> follow; TODO : Social package
    // private ArrayList<String> friends = null; TODO : Social package
    // private ArrayList<String> friendRequest = null; TODO : Social package
    // private String language = null; TODO : Language package

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public User(String username, String Password, Connection connect) {
        this.username = username;
        this.familyName = "Dalton";
        this.firstName = "John";
        this.emailAddress = "john@dalton.us";
        this.password = Password;
        this.database = connect;
        this.cookbook = new ArrayList<Recipe>();
    }

    public void ForkRecipe (UUID forkedRecipeId) {
        Recipe original = this.database.recettes.getRecipe(forkedRecipeId);

        UUID newId = UUID.randomUUID();
        Recipe forkedRecipe = new Recipe();

        forkedRecipe.setAuthor(this.username);
        forkedRecipe.setLicence(original.getLicence());
        forkedRecipe.setName(original.getName());
        forkedRecipe.setRecipeId(newId);
        forkedRecipe.setOriginal_recipe(original.getRecipeId());
        forkedRecipe.setInstructions(original.getInstructions());

        this.cookbook.add(forkedRecipe);

    }


}
