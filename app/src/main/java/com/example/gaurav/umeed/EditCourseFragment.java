package com.example.gaurav.umeed;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Gaurav on 26-03-2017.
 */
public class EditCourseFragment extends Fragment {

    EditText courseName,description,category,start_date,duration;
    ListView lv;
    Button update;
    ArrayList<ModuleModel> data=new ArrayList<ModuleModel>();
    EditModuleAdapter arrayAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.editcourse_layout, container, false);

        courseName=(EditText)view.findViewById(R.id.coursename);
        description=(EditText)view.findViewById(R.id.description);
        category=(EditText)view.findViewById(R.id.category);
        start_date=(EditText)view.findViewById(R.id.start_date);
        duration=(EditText)view.findViewById(R.id.duration);
        update=(Button)view.findViewById(R.id.update);
        lv=(ListView)view.findViewById(R.id.modulelist);
        data=new ArrayList<ModuleModel>();
        ModuleModel mm=new ModuleModel("module_id","main_course","module_name","module_description","video_path");
        data.add(mm);
        arrayAdapter =new EditModuleAdapter(data,getActivity());
        lv.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
        //getCourses();

        return view;

    }

}
