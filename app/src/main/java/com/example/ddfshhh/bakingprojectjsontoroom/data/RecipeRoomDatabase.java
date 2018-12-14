package com.example.ddfshhh.bakingprojectjsontoroom.data;

import android.content.Context;

import com.example.ddfshhh.bakingprojectjsontoroom.model.Ingredient;
import com.example.ddfshhh.bakingprojectjsontoroom.model.Recipe;
import com.example.ddfshhh.bakingprojectjsontoroom.model.Step;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Recipe.class, Ingredient.class, Step.class}, version = 1)
public abstract class RecipeRoomDatabase extends RoomDatabase {

    private static RecipeRoomDatabase INSTANCE;

    public abstract RecipeDao getRecipeDao();
    public abstract IngredientDao getIngredientDao();
    public abstract StepDao getStepDao();

    public static RecipeRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RecipeRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RecipeRoomDatabase.class, "recipe_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
