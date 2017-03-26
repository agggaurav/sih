package com.example.gaurav.umeed;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Gaurav on 25-03-2017.
 */
public class ApplicantFragment extends Fragment {

    ListView lv;
    ArrayList<CPModel> data=new ArrayList<CPModel>();
    CPAdapter arrayAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.applicant_layout, container, false);

            lv=(ListView)view.findViewById(R.id.courses);

        data=new ArrayList<CPModel>();
        CPModel cp=new CPModel();
        HashMap<String,Double> marks=new HashMap<>();
        marks.put("module1",5.0);
        marks.put("module2",10.0);
        marks.put("module3",4.0);
        cp.setCourseId("1");
        cp.setCourseName("Web DEvelopment");
        cp.setDuration("2 months");
        cp.setModelEval(marks);
        data.add(cp);
        arrayAdapter =new CPAdapter(data,getActivity());
        lv.setAdapter(arrayAdapter);
        CPModel cp2=new CPModel();
        cp2.setCourseId("2");
        cp2.setCourseName("Angular js");
        cp2.setDuration("2 months");
        cp2.setModelEval(marks);
        data.add(cp2);
        arrayAdapter.notifyDataSetChanged();
        return view;
    }
    }
