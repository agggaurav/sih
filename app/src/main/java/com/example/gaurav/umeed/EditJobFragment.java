package com.example.gaurav.umeed;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
 * Created by Gaurav on 29-03-2017.
 */
public class EditJobFragment extends Fragment {

    public static String POSTJOB_URL = "";
    public static String POSTJOBSKILL_URL = "";
    EditText title,description,vacancies,location,stipend;
    TextView skills;
    Button update;
    ArrayList<String> values =new  ArrayList<String>();
    ArrayAdapter<String> mArrayAdapter;
    Boolean checked;
    ArrayList<String> skill = new ArrayList<String>();
    Button addskill;
    JobModel job;
    //
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.jobform_layout, container, false);
        POSTJOB_URL=Constants.ip;

        POSTJOBSKILL_URL=Constants.ip;

        Bundle bundle = getArguments();
        job= (JobModel) bundle.getParcelable("jb");
        POSTJOB_URL=POSTJOB_URL+"jobid/"+job.getJob_id()+"/";
        POSTJOBSKILL_URL=POSTJOBSKILL_URL+"jobskill/"+job.getJob_id()+"/";
        title=(EditText)view.findViewById(R.id.title);
        description=(EditText)view.findViewById(R.id.description);
        location=(EditText)view.findViewById(R.id.location);
        vacancies=(EditText)view.findViewById(R.id.vacancies);
        stipend=(EditText)view.findViewById(R.id.stipend);
        skills=(TextView)view.findViewById(R.id.skills_require);
        update=(Button)view.findViewById(R.id.create_job);
        addskill=(Button)view.findViewById(R.id.addskill);
title.setText(job.getTitle());
        description.setText(job.getDesc());
        location.setText(job.getLocation());
        vacancies.setText(job.getVacancies());
        stipend.setText(job.getStipend());

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updatejob(description.getText().toString(), location.getText().toString(), vacancies.getText().toString(), stipend.getText().toString());
                        String skill=skills.getText().toString();
                        if(skill.length()>1) {
                            skill.substring(0, skill.length() - 1);
                            createjobskill(skill);
                        }
                    }
                });

        addskill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getActivity());
                // Include dialog.xml file
                dialog.setContentView(R.layout.skill_dialog);
                skills.setText("");


                final String[] selectedskill = {""};
                mArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_multiple_choice, android.R.id.text1, values);

                final ListView[] skilllist = {(ListView) dialog.findViewById(R.id.list_skill_dialog)};

                skilllist[0].setAdapter(mArrayAdapter);

                Button submitskill = (Button) dialog.findViewById(R.id.skill_dialog_btn);

                skilllist[0].setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        SparseBooleanArray sp = skilllist[0].getCheckedItemPositions();
                        selectedskill[0] = "";
                        String str = "";
                        for (int i = 0; i < sp.size(); i++) {
                            str += values.get(sp.keyAt(i)) + ",";
                        }
                        Toast.makeText(getActivity(), "" + str, Toast.LENGTH_SHORT).show();
                        selectedskill[0] = str;

                    }
                });

                submitskill.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        skills.setText(selectedskill[0]);
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });


        return view;
    }



    public void updatejob(final String desc, final String loc, final String vacancies, final String stipend)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, POSTJOB_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("companyfrom","1");
                params.put("description",desc);
                params.put("vacancies", vacancies);
                //  params.put("skill",skill);
                params.put("stipend",stipend);
                params.put("location",loc);
//            params.put("last_date",String.valueOf(Date.valueOf(last_date.getText().toString())));
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    public void createjobskill(final String skill) {
        final String[] skilllist = skill.split(",");
        for (int k = 0; k < skilllist.length; k++)
        {
            final int finalK = k;
            StringRequest stringRequest = new StringRequest(Request.Method.POST, POSTJOBSKILL_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("name", skilllist[finalK]);
                    params.put("job_from",job.getJob_id());//this needs to be changed
//            params.put("last_date",String.valueOf(Date.valueOf(last_date.getText().toString())));
                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.add(stringRequest);
        }

    }



    }
