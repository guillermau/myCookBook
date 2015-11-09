package eu.tools.media.mycookbook.model;

public class Database {

    private String hostname;
    private String port;
    private String dbname;
    private String password;
    private String username;

    private User mySelf;
    public static Ingredients ingredients;
    public static Recipes recettes;
    private static Connection connexion;

    public Database() {

    } // TODO : load data

    public boolean login (String username, String password) {
        this.mySelf = new User(username, password, this.connexion);

        return true;
    }

    public boolean loginDatabase () {
        return false;
    } // TODO : Database Package

}
