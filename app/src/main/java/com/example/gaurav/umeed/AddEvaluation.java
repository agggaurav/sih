package com.example.gaurav.umeed;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Gaurav on 27-03-2017.
 */
public class AddEvaluation extends Fragment {
    ArrayList<String> options;
    ListView lv;
    EditText ques;
    Button addques;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.addevaluation_layout, container, false);

        ques=(EditText)view.findViewById(R.id.ques);
        lv=(ListView)view.findViewById(R.id.optionlist);
        addques=(Button)view.findViewById(R.id.addques);

        return view;
    }
    }

