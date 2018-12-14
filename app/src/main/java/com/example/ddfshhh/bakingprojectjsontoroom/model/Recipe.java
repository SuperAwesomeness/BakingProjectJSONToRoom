package com.example.ddfshhh.bakingprojectjsontoroom.model;


import java.util.List;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName ="recipe_table")
public class Recipe {

    @PrimaryKey
    private int id;

    private String name;

    private int servings;

    private String image;

    // we ignore this because we don't want to generate this column in the database. It's
    // defined here only because it's required for GSON to parse the JSON data
    @Ignore
    public List<Ingredient> ingredients;

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    // we ignore this because we don't want to generate this column in the database. It's
    // defined here because it's required for GSON to parse the JSON data
    @Ignore
    public List<Step> steps;

    /**
     * The important thing to know is that Room must have only one constructor to deal
     * with. Thus use @Ignore on other constructor(s) to tell Room not to bother.
     * @param id
     * @param name
     * @param servings
     * @param image
     */
    public Recipe(int id, String name, int servings, String image) {
        this.id = id;
        this.name = name;
        this.servings = servings;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
