package com.example.ghazanfarali.piggyland.Views.Activities.PrintOrder.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class Attachment implements Parcelable {

    String fileName;
    String filePath;
    String serverURL;

    public Attachment(String filename, String filepath) {
        this.fileName = filename;
        this.filePath = filepath;
    }

    public Attachment(String fileName, String filePath, String serverURL) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.serverURL = serverURL;
    }

    public String getServerURL() {
        return serverURL;
    }

    public void setServerURL(String serverURL) {
        this.serverURL = serverURL;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath the filePath to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    protected Attachment(Parcel in) {
        fileName = in.readString();
        filePath = in.readString();
        serverURL = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fileName);
        dest.writeString(filePath);
        dest.writeString(serverURL);
    }

    public static final Creator<Attachment> CREATOR = new Creator<Attachment>() {
        @Override
        public Attachment createFromParcel(Parcel in) {
            return new Attachment(in);
        }

        @Override
        public Attachment[] newArray(int size) {
            return new Attachment[size];
        }
    };
}