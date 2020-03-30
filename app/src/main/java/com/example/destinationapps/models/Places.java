package com.example.destinationapps.models;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Places implements Parcelable {
    private String title;
    private String description;
    private String city;
    private Uri image;

    public Places() {
    }

    public Places(String title, String description, String city, Uri image) {
        this.title = title;
        this.description = description;
        this.city = city;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.city);
        dest.writeParcelable(this.image, flags);
    }

    protected Places(Parcel in) {
        this.title = in.readString();
        this.description = in.readString();
        this.city = in.readString();
        this.image = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<Places> CREATOR = new Creator<Places>() {
        @Override
        public Places createFromParcel(Parcel source) {
            return new Places(source);
        }

        @Override
        public Places[] newArray(int size) {
            return new Places[size];
        }
    };
}
