package com.example.gaurav.umeed;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Gaurav on 25-03-2017.
 */
public class CPModel {

    String courseId;
    String courseName;
    String duration;
    String completion;
    String enrolled_on;
    HashMap<String,Double> a;

    public CPModel()
    {

    }

    public HashMap<String,Double> getModelEval()
    {
        return a;
    }

    public void setModelEval(HashMap<String, Double> a) {
        this.a = a;
    }

    public String getCourseId()
    {
        return courseId;
    }
    public String getCourseName()
    {
        return courseName;
    }

    public String getDuration() {
        return duration;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public HashMap<String, Double> getA() {
        return a;
    }

    public String getCompletion() {
        return completion;
    }

    public String getEnrolled_on() {
        return enrolled_on;
    }

    public void setA(HashMap<String, Double> a) {
        this.a = a;
    }

    public void setCompletion(String completion) {
        this.completion = completion;
    }

    public void setEnrolled_on(String enrolled_on) {
        this.enrolled_on = enrolled_on;
    }
}
