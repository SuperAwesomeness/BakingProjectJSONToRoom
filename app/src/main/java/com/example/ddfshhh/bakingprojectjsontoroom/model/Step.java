package com.example.ddfshhh.bakingprojectjsontoroom.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName ="step_table")
public class Step {

    @PrimaryKey(autoGenerate = true)
    private int idStep;

    // this maps to the id data from the JSON
    // NOTE that this can NOT be the primary key because different steps from different
    // recipes might have the same ids (e.g., Step 1 from Nutella and Step 1 from Brownies
    // both have an id of 1 so there will be an error when we try to insert the data)
    private int id;

    private String shortDescription;

    private String description;

    private String videoURL;

    private String thumbnailURL;

    private int idRecipe;

    public int getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(int idRecipe) {
        this.idRecipe = idRecipe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public int getIdStep() {
        return idStep;
    }

    public void setIdStep(int idStep) {
        this.idStep = idStep;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    /**
     * The important thing to know is that Room must have only one constructor to deal
     * with. Thus use @Ignore on other constructor(s) to tell Room not to bother.
     * @param id
     * @param shortDescription
     * @param description
     * @param videoURL
     * @param thumbnailURL
     * @param
     */
    public Step(int id, String shortDescription, String description, String videoURL, String thumbnailURL, int idRecipe) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
        this.idRecipe = idRecipe;
    }
}
