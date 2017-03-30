package com.example.gaurav.umeed;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Gaurav on 21-03-2017.
 */
public class JobModel implements Parcelable {
    String company;
    String desc;
    String vacancies;
    String stipend;
    String location;
    String date_of_posting;
    String title;
    String job_id;
    String last_date;
    String skill;
    //String feature;

    public JobModel(String job_id,String company, String desc, String vacancies,String stipend,String location,String title,String last_date,String date_of_posting) {
        this.company=company;
        this.desc=desc;
        this.vacancies=vacancies;
        this.job_id=job_id;
        this.date_of_posting=date_of_posting;
        this.stipend=stipend;
        this.location=location;
        this.title=title;
        this.last_date=last_date;


    }

    protected JobModel(Parcel in) {
        company = in.readString();
        desc = in.readString();
        vacancies = in.readString();
        stipend = in.readString();
        location = in.readString();
        date_of_posting = in.readString();
        title = in.readString();
        job_id = in.readString();
        last_date = in.readString();
    }

    public static final Creator<JobModel> CREATOR = new Creator<JobModel>() {
        @Override
        public JobModel createFromParcel(Parcel in) {
            return new JobModel(in);
        }

        @Override
        public JobModel[] newArray(int size) {
            return new JobModel[size];
        }
    };

    public String getCompany() {
        return company;
    }

    public String getDesc() {
        return desc;
    }

    public String getVacancies() {
        return vacancies;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public void setCompany(String company) {
        this.company=company;
    }

    public void setDesc(String desc) {
        this.desc=desc;
    }

    public void setVacancies(String vacancies) {
        this.vacancies=vacancies;
    }

    public String getDate_of_posting() {
        return date_of_posting;
    }

    public String getJob_id() {
        return job_id;
    }

    public String getLast_date() {
        return last_date;
    }

    public String getLocation() {
        return location;
    }

    public String getStipend() {
        return stipend;
    }

    public String getTitle() {
        return title;
    }

    public void setDate_of_posting(String date_of_posting) {
        this.date_of_posting = date_of_posting;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public void setLast_date(String last_date) {
        this.last_date = last_date;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStipend(String stipend) {
        this.stipend = stipend;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(company);
        dest.writeString(desc);
        dest.writeString(vacancies);
        dest.writeString(stipend);
        dest.writeString(location);
        dest.writeString(date_of_posting);
        dest.writeString(title);
        dest.writeString(job_id);
        dest.writeString(last_date);
    }

    /*
    public String getFeature() {
        return feature;
    }
*/
}