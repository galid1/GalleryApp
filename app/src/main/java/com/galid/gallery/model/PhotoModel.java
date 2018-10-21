package com.galid.gallery.model;

public class PhotoModel {
    private String photoName;
    private String photoUrl;

    public PhotoModel(){}

    public PhotoModel(String photoName, String photoUrl) {
        this.photoName = photoName;
        this.photoUrl = photoUrl;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
