package com.example.gaurav.umeed;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Gaurav on 25-03-2017.
 */
public class ApplicantModel implements Parcelable {
    String applicantId;
    String applicantName;
    String location;
    ArrayList<String> interests;
    ArrayList<String> skills;

    public ApplicantModel(String applicantId,String applicantName,String location,ArrayList<String> interests,ArrayList<String> skills)
    {
        this.applicantId=applicantId;
        this.applicantName=applicantName;
        this.location=location;
        this.interests=interests;
        this.skills=skills;
    }

    protected ApplicantModel(Parcel in) {
        applicantName = in.readString();
        location = in.readString();
        interests = in.createStringArrayList();
        skills = in.createStringArrayList();
    }

    public static final Creator<ApplicantModel> CREATOR = new Creator<ApplicantModel>() {
        @Override
        public ApplicantModel createFromParcel(Parcel in) {
            return new ApplicantModel(in);
        }

        @Override
        public ApplicantModel[] newArray(int size) {
            return new ApplicantModel[size];
        }
    };

    public String getApplicantName()
    {
        return applicantName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(applicantName);
        dest.writeString(location);
        dest.writeStringList(interests);
        dest.writeStringList(skills);
    }
}
