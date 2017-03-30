package com.example.gaurav.umeed;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Gaurav on 26-03-2017.
 */
public class PreviousCoursesFragment extends Fragment {
    ArrayList<CourseModel> data=new ArrayList<CourseModel>();
    ListView lv;
    PreviousCourseAdapter arrayAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.previouscourses_layout, container, false);

        lv=(ListView)view.findViewById(R.id.courselist);
        data=new ArrayList<CourseModel>();
        CourseModel cm=new CourseModel("founder","name","category");
        cm.setEnrolled("30");
        cm.setDate("20/12/2015");
        cm.setDetail("Description Coming soon");
        data.add(cm);
        CourseModel cm2=new CourseModel("founder2","name2","category2");
        cm2.setEnrolled("320");
        cm2.setDate("20/2/2015");
        cm2.setDetail("dfdsDEscription Coming soon");
        data.add(cm2);
        arrayAdapter =new PreviousCourseAdapter(data,getActivity());
        lv.setAdapter(arrayAdapter);
        //loadcourses();

        return view;

    }
    }
