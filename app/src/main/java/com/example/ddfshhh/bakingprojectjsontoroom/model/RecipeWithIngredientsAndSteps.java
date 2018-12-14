package com.example.ddfshhh.bakingprojectjsontoroom.model;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;

/**
 * There’s also another way of providing relationships using Room — with a @Relation
 * annotation. You can declare such a relation only inside non-entity class.
 *
 * https://android.jlelse.eu/android-architecture-components-room-relationships-bf473510c14a
 */
public class RecipeWithIngredientsAndSteps {

    @Embedded
    public Recipe recipe;

    /**
     * parentColumn "id" is the id column from the Recipe table
     * entityColumn "idRecipe" is the recipe id column from the Step table
     */
    @Relation(parentColumn = "id",
            entityColumn = "idRecipe") public List<Step> stepList;

    /**
     * parentColumn "id" is the id column from the Recipe table
     * entityColumn "idRecipe" is the recipe id column from the Ingredient table
     */
    @Relation(parentColumn = "id",
            entityColumn = "idRecipe") public List<Ingredient> ingredientList;


}
