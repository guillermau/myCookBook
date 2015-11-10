package eu.tools.media.mycookbook.model;

import java.util.ArrayList;
import java.util.UUID;

public class User {

    private Connection connection;
    private String userId = null;
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

    public ArrayList<Recipe> getCookbook() {
        return cookbook;
    }

    public User(Connection connection, String username, String Password) {
        this.connection = connection;
        this.username = username;
        this.familyName = "Dalton";
        this.firstName = "John";
        this.emailAddress = "john@dalton.us";
        this.password = Password;
        this.cookbook = new ArrayList<Recipe>();
        this.cookbook.add(new Recipe(connection, "1"));
    }

//    public void ForkRecipe (String forkedRecipeId) {
//        Recipe original =this.connection.getRecipe(forkedRecipeId);
//
//        UUID newId = UUID.randomUUID();
//        Recipe forkedRecipe = new Recipe();
//
//        forkedRecipe.setAuthor(this.username);
//        forkedRecipe.setLicence(original.getLicence());
//        forkedRecipe.setName(original.getName());
//        forkedRecipe.setRecipeId("1A5921A0-872A-11E5-9316-A5F464696657");
//        forkedRecipe.setOriginal_recipe(original.getRecipeId());
//        forkedRecipe.setInstructions(original.getInstructions());
//
//        this.cookbook.add(forkedRecipe);
//
//    }

    public boolean login () {
        return true;
    }


}
