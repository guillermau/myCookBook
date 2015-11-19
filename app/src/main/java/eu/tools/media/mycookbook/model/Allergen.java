package eu.tools.media.mycookbook.model;

import java.util.UUID;

public class Allergen {

    private UUID allergenId;
    private String name;
    // public String translation; TODO : Language package



    public Allergen(UUID allergenId) {
        this.allergenId = allergenId;
    }

    public Allergen(String name) {
        this.name = name;
    }

    public Allergen(UUID allergenId, String name) {
        this.allergenId = allergenId;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
