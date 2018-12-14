package com.example.ddfshhh.bakingprojectjsontoroom;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.ddfshhh.bakingprojectjsontoroom.data.IngredientDao;
import com.example.ddfshhh.bakingprojectjsontoroom.data.JsonLoader;
import com.example.ddfshhh.bakingprojectjsontoroom.data.RecipeDao;
import com.example.ddfshhh.bakingprojectjsontoroom.data.RecipeRoomDatabase;
import com.example.ddfshhh.bakingprojectjsontoroom.data.StepDao;
import com.example.ddfshhh.bakingprojectjsontoroom.model.Ingredient;
import com.example.ddfshhh.bakingprojectjsontoroom.model.Recipe;
import com.example.ddfshhh.bakingprojectjsontoroom.model.RecipeWithIngredientsAndSteps;
import com.example.ddfshhh.bakingprojectjsontoroom.model.Step;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecipeRoomDatabase db = RecipeRoomDatabase.getDatabase(this);

        new PopulateDbAsync(db, this).execute();
    }

    /**
     * Populate the database in the background.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, String> {

        private final RecipeDao mRecipeDao;
        private final IngredientDao mIngredientDao;
        private final StepDao mStepDao;

        private Activity mContext;

        PopulateDbAsync(RecipeRoomDatabase db, Activity context) {
            mContext = context;
            mRecipeDao = db.getRecipeDao();
            mIngredientDao = db.getIngredientDao();
            mStepDao = db.getStepDao();
        }

        @Override
        protected String doInBackground(final Void... params) {
            try {
                mRecipeDao.deleteAll();
                mIngredientDao.deleteAll();
                mStepDao.deleteAll();

                String jsonString = JsonLoader.getJSONString(mContext, R.raw.baking);
                Type listType = new TypeToken<List<Recipe>>() {}.getType();
                List<Recipe> recipeList = new Gson().fromJson(jsonString, listType);
                mRecipeDao.insert(recipeList);
                for (int i=0;i<recipeList.size();i++) {
                    int recipeId = recipeList.get(i).getId();
                    List<Ingredient> ingredientListForSingleRecipe = recipeList.get(i).ingredients;
                    Log.i("Main", "inserting ingredient list size: " + ingredientListForSingleRecipe.size());

                    // we need to manually set the recipe id so that later we can retrieve
                    // joined data from different tables
                    for (int j=0;j<ingredientListForSingleRecipe.size();j++) {
                        ingredientListForSingleRecipe.get(j).setIdRecipe(recipeId);
                    }

                    mIngredientDao.insert(ingredientListForSingleRecipe);

                    List<Step> stepListForSingleRecipe = recipeList.get(i).steps;
                    Log.i("Main", "inserting step list size: " + stepListForSingleRecipe.size());

                    // we need to manually set the recipe id so that later we can retrieve
                    // joined data from different tables (a list of "RecipeWithIngredientsAndSteps"s.)
                    for (int k=0;k<stepListForSingleRecipe.size();k++) {
                        stepListForSingleRecipe.get(k).setIdRecipe(recipeId);
                        Log.i("Main", "setting recipe id to step item: " + recipeId);
                    }
                    mStepDao.insert(stepListForSingleRecipe);
                }

                // fetch data from database
                List<RecipeWithIngredientsAndSteps> recipeWithIngredientsAndSteps = mRecipeDao.getAllRecipes();
                String textToDisplay = "";
                RecipeWithIngredientsAndSteps recipe;
                for (int i=0;i<recipeWithIngredientsAndSteps.size();i++) {
                    recipe = recipeWithIngredientsAndSteps.get(i);
                    String recipeName = recipe.recipe.getName();
                    int ingredientCount = recipe.ingredientList.size();
                    int stepCount = recipe.stepList.size();
                    textToDisplay += recipeName + " has " + ingredientCount +
                            " ingredient(s) and " + stepCount + " step(s)\n";
                }
                return textToDisplay;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            TextView textView = mContext.findViewById(R.id.recipes);
            textView.setText(s);
        }
    }
}
