package com.example.ddfshhh.bakingprojectjsontoroom.data;

import com.example.ddfshhh.bakingprojectjsontoroom.model.Recipe;
import com.example.ddfshhh.bakingprojectjsontoroom.model.RecipeWithIngredientsAndSteps;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<Recipe> recipes);

    /**
     * Here we are retrieving a list of RecipeWithIngredientsAndSteps instead of just Recipes.
     * @return
     */
    @Query("SELECT * FROM recipe_table")
    List<RecipeWithIngredientsAndSteps> getAllRecipes();

    @Query("DELETE FROM recipe_table")
    void deleteAll();
}
