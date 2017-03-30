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
    String education;
    String dob;
    String contact;
    String email;
    String gender;

    ArrayList<String> interests;
    ArrayList<String> skills;
    ArrayList<CPModel> courses;

    public ApplicantModel(String applicantId,String applicantName,String location)
    {
        this.applicantId=applicantId;
        this.applicantName=applicantName;
        this.location=location;

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

    public String getApplicantId() {
        return applicantId;
    }

    public ArrayList<CPModel> getCourses() {
        return courses;
    }

    public ArrayList<String> getInterests() {
        return interests;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public String getLocation() {
        return location;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public void setCourses(ArrayList<CPModel> courses) {
        this.courses = courses;
    }

    public void setInterests(ArrayList<String> interests) {
        this.interests = interests;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getDob() {
        return dob;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
