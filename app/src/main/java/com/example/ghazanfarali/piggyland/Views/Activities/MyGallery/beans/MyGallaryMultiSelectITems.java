package com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.beans;

import java.io.Serializable;

/**
 * Created by Amir.jehangir on 1/15/2017.
 */
public class MyGallaryMultiSelectITems implements Serializable {
    private String smartToolsTitle;
    private String smartToolsId;
    private String smartToolsImageURL;
    private boolean smartToolsCheckbox;

    public String getmygallaryTitle() {
        return smartToolsTitle;
    }

    public void setmygallaryTitle(String smartToolsTitle) {
        this.smartToolsTitle = smartToolsTitle;
    }

    public String getmygallaryId() {
        return smartToolsId;
    }

    public void setmygallaryId(String smartToolsId) {
        this.smartToolsId = smartToolsId;
    }

    public String getmygallaryImageURL() {
        return smartToolsImageURL;
    }

    public void setmygallaryImageURL(String smartToolsImageURL) {
        this.smartToolsImageURL = smartToolsImageURL;
    }


    public boolean getmygallarycheckbox() {
        return smartToolsCheckbox;
    }

    public void setmygallarycheckbox(boolean smartToolsTitle) {
        this.smartToolsCheckbox = smartToolsTitle;
    }


}
