package com.example.ddfshhh.bakingprojectjsontoroom.data;

import com.example.ddfshhh.bakingprojectjsontoroom.model.Step;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface StepDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<Step> steps);

    @Query("DELETE FROM step_table")
    void deleteAll();
}
