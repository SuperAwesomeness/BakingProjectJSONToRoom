package com.example.ddfshhh.bakingprojectjsontoroom.data;

import com.example.ddfshhh.bakingprojectjsontoroom.model.Ingredient;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface IngredientDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<Ingredient> ingredients);

    @Query("DELETE FROM ingredient_table")
    void deleteAll();
}
