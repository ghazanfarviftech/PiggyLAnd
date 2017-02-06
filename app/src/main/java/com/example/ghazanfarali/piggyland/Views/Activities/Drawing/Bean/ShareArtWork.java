package com.example.ghazanfarali.piggyland.Views.Activities.Drawing.Bean;

import java.io.File;

/**
 * Created by ghazanfarali on 19/01/2017.
 */

public class ShareArtWork {
    private String smartToolsTitle;
    private String smartToolsLikes;
    private String smartToolsComments;
    private File smartToolsImageURL;

    public String getmygallaryTitle() {
        return smartToolsTitle;
    }

    public void setmygallaryTitle(String smartToolsTitle) {
        this.smartToolsTitle = smartToolsTitle;
    }

    public File getmygallaryImageURL() {
        return smartToolsImageURL;
    }

    public void setmygallaryImageURL(File smartToolsImageURL) {
        this.smartToolsImageURL = smartToolsImageURL;
    }

    public String getSmartToolsComments() {
        return smartToolsComments;
    }

    public void setSmartToolsComments(String smartToolsComments) {
        this.smartToolsComments = smartToolsComments;
    }

    public String getSmartToolsLikes() {
        return smartToolsLikes;
    }

    public void setSmartToolsLikes(String smartToolsLikes) {
        this.smartToolsLikes = smartToolsLikes;
    }
}
