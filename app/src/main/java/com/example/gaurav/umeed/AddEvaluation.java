package com.example.gaurav.umeed;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gaurav on 27-03-2017.
 */
public class AddEvaluation extends Fragment {
    ArrayList<OptionModel> options;
    ListView lv;
    EditText ques;
    Button addopt;
    OptionAdapter arrayAdapter;
    String ans;
    Button addques;
    private static final String TEST_URL = "";
String course;
    String module;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.addevaluation_layout, container, false);
        Bundle bundle = getArguments();
        course=  bundle.getString("course_id");
        module=  bundle.getString("module_id");

        ques=(EditText)view.findViewById(R.id.ques);
        lv=(ListView)view.findViewById(R.id.optionlist);
        addopt=(Button)view.findViewById(R.id.add_option);
        options=new ArrayList<>();
        arrayAdapter=new OptionAdapter(options,getActivity());
        lv.setAdapter(arrayAdapter);
        addques=(Button)view.findViewById(R.id.addques);

        addopt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getActivity());
                // Include dialog.xml file
                dialog.setContentView(R.layout.option_dialog);
                // Set dialog title
                dialog.setTitle("ADD OPTION");
                final EditText qwerty=(EditText) dialog.findViewById(R.id.opt_dialog);
                Button apply=(Button) dialog.findViewById(R.id.opt_ok);
                dialog.show();
                apply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OptionModel om=new OptionModel(qwerty.getText().toString());
                        options.add(om);
                        dialog.dismiss();
                    }
                });

            }
        });

        addques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ans="";
                for (int i=0;i<options.size();i++)
                {
                    OptionModel pm=(OptionModel) lv.getItemAtPosition(i);
                    ans=ans+ pm.getValue();
                }
                Toast.makeText(getActivity(),ans,Toast.LENGTH_SHORT).show();
                add_ques(ans);
            }
        });

        return view;
    }


    private void add_ques(final String ans){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, TEST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getActivity(),response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("module",module);
                params.put("question",ques.getText().toString());
                params.put("option1", options.get(0).getOption());
                params.put("option2", options.get(1).getOption());
                params.put("option3", options.get(2).getOption());
                params.put("option4", options.get(3).getOption());
                params.put("answer",ans);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    }

