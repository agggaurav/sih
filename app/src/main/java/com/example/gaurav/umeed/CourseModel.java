package com.example.gaurav.umeed;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Gaurav on 23-03-2017.
 */
public class CourseModel implements Parcelable {

    String founder;
    String name;
    String category;
    String detail;
    String enrolled;
    String date;
    String id;

    public CourseModel(String founder,String name,String category)
    {
        this.category=category;
        this.founder=founder;
        this.name=name;
    }

    protected CourseModel(Parcel in) {
        founder = in.readString();
        name = in.readString();
        category = in.readString();
        detail = in.readString();
    }

    public static final Creator<CourseModel> CREATOR = new Creator<CourseModel>() {
        @Override
        public CourseModel createFromParcel(Parcel in) {
            return new CourseModel(in);
        }

        @Override
        public CourseModel[] newArray(int size) {
            return new CourseModel[size];
        }
    };

    public String getFounder()
    {
        return founder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public String getCategory()
    {
        return category;
    }


    public String getDetail()
    {
        return detail;
    }

    public void setFounder(String founder)
    {
        this.founder=founder;
    }

    public void setName(String name)
    {
        this.name=name;
    }
    public void setCategory(String category)
    {
        this.category=category;
    }
    public void setDetail(String detail)
    {
        this.detail=detail;
    }

    public String getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(String enrolled) {
        this.enrolled = enrolled;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(founder);
        dest.writeString(name);
        dest.writeString(category);
        dest.writeString(detail);
    }
}
