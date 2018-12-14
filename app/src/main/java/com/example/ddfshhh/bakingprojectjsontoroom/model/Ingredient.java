package com.example.ddfshhh.bakingprojectjsontoroom.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName ="ingredient_table")
public class Ingredient {

    /**
     * There is no ingredient id data in the JSON so we have to auto-generate it.
     */
    @PrimaryKey(autoGenerate = true)
    private int id;

    private double quantity;

    private String measure;

    private String ingredient;

    private int idRecipe;

    /**
     * The important thing to know is that Room must have only one constructor to deal
     * with. Thus use @Ignore on other constructor(s) to tell Room not to bother.
     * @param id
     * @param quantity
     * @param measure
     * @param ingredient
     * @param
     */
    public Ingredient(int id, double quantity, String measure, String ingredient, int idRecipe) {
        this.id = id;
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
        this.idRecipe = idRecipe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double mQuantity) {
        this.quantity = mQuantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String mMeasure) {
        this.measure = mMeasure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public int getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(int idRecipe) {
        this.idRecipe = idRecipe;
    }

    public void setIngredient(String mIngredient) {
        this.ingredient = mIngredient;
    }
}
