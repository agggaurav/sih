package com.example.gaurav.umeed;

/**
 * Created by Gaurav on 21-03-2017.
 */
public class JobModel {
    String company;
    String desc;
    String vacancies;
    //String feature;

    public JobModel(String company, String desc, String vacancies) {
        this.company=company;
        this.desc=desc;
        this.vacancies=vacancies;


    }

    public String getCompany() {
        return company;
    }

    public String getDesc() {
        return desc;
    }

    public String getVacancies() {
        return vacancies;
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

/*
    public String getFeature() {
        return feature;
    }
*/
}