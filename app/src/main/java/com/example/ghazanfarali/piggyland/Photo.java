package com.example.ghazanfarali.piggyland;

import java.io.File;
import java.io.Serializable;

/**
 * Created by jean on 19/07/16.
 */

public class Photo implements Serializable {
    private String description = "";
    private File imageUrl;
    private boolean smartToolsCheckbox;

    public Photo(){}

    public Photo(File imageUrl){
        this.imageUrl = imageUrl;
    }

    public Photo(File imageUrl, String description){
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public File getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(File imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean getmygallarycheckbox() {
        return smartToolsCheckbox;
    }

    public void setmygallarycheckbox(boolean smartToolsTitle) {
        this.smartToolsCheckbox = smartToolsTitle;
    }
}
