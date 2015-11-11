package eu.tools.media.mycookbook.model;

public class UsedIngredient {
    Ingredient usedIngredient;
    Integer quantity;
    String unit;

    public UsedIngredient(Ingredient ingredient, Integer quantity, String unit) {
        this.usedIngredient = ingredient;
        this.quantity = quantity;
        this.unit = unit;
    }

    public Ingredient getUsedIngredient() {
        return usedIngredient;
    }

    public String getUnit() {
        return unit;
    }

    public Integer getQuantity() {

        return quantity;
    }
}
