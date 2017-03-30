package com.example.gaurav.umeed;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gaurav on 26-03-2017.
 */
public class AddCourseFragment extends Fragment {
    EditText courseName,category,description,videourl,moduleDesc,moduleName;
    RadioGroup rg;
    RadioButton online,offline;
    LinearLayout l1,l2;
    Spinner s;
    Button addModule;
    int width,height;
    Button insertModule;
    List<String> data = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;
    FloatingActionButton submit;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.addcourse_layout, container, false);
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        insertModule=(Button)view.findViewById(R.id.insertmodule);
        addModule=(Button)view.findViewById(R.id.addmodule);
        courseName=(EditText)view.findViewById(R.id.courseName);
        category=(EditText)view.findViewById(R.id.category);
        description=(EditText)view.findViewById(R.id.desc);
        videourl=(EditText)view.findViewById(R.id.videourl);
        rg = (RadioGroup) view.findViewById(R.id.mode);
        moduleDesc=(EditText)view.findViewById(R.id.moduledesc);
        moduleName=(EditText)view.findViewById(R.id.modulename);
        l1=(LinearLayout)view.findViewById(R.id.checkmode);
        l1.setVisibility(View.INVISIBLE);
        l2=(LinearLayout)view.findViewById(R.id.moduleinfo);
        submit = (FloatingActionButton) view.findViewById(R.id.addcourse);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (String.valueOf(checkedId).equals("1")) {
                    load();

                }

                if (String.valueOf(checkedId).equals("2")) {
                    hide();

                }

                // checkedId is the RadioButton selected
            }
        });

         s = (Spinner) view.findViewById(R.id.Spinner01);

         arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,data );
        s.setAdapter(arrayAdapter);
        insertModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            data.add(moduleName.getText().toString());
                moduleDesc.setText("");
                moduleName.setText("");
                videourl.setText("");
                arrayAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    public void load()
    {
        Toast.makeText(getActivity(),"load",Toast.LENGTH_SHORT).show();
        l1.setVisibility(View.VISIBLE);

        addModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT); // or set height to any fixed value you want
                l2.setLayoutParams(lp);
                         }



        });


    }

    public void hide()
    {
        l1.setVisibility(View.INVISIBLE);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0,0); // or set height to any fixed value you want
        l2.setLayoutParams(lp);
    }
}
