package com.example.gaurav.umeed;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gaurav on 25-03-2017.
 */
public class JobpostFragment extends Fragment {

    FloatingActionButton fab;
    JobModel jm;
    ArrayList<JobModel> data=new ArrayList<JobModel>();
    ListView lv;
    JobAdapter arrayAdapter;
    public String load_jobs =Constants.ip; //"http://192.168.1.101:8000/loadpostedjobs/companyid";
    public static String GETJOBSKILL_URL = "";
    String jsonResponse;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    String company_id="2";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.postjob_layout, container, false);
        load_jobs=Constants.ip;
        load_jobs=load_jobs+"loadjobs/"+company_id+"/";
        GETJOBSKILL_URL=Constants.ip;
        GETJOBSKILL_URL=GETJOBSKILL_URL+"jobskill/";
        lv=(ListView)view.findViewById(R.id.posted_jobs);
        data=new ArrayList<JobModel>();
        arrayAdapter =new JobAdapter(data,getActivity());
        lv.setAdapter(arrayAdapter);
        getjobs();
        fab = (FloatingActionButton) view.findViewById(R.id.addjob);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                mFragmentManager = getActivity().getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();
               mFragmentTransaction.replace(R.id.containerView,new JobFormFragment()).commit();

            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mFragmentManager = getActivity().getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();

                Bundle bundles = new Bundle();
                JobModel jb =(JobModel) parent.getItemAtPosition(position);
                GETJOBSKILL_URL=GETJOBSKILL_URL+jb.getJob_id()+"/";
                String sk=getskill(jb.getJob_id());
                if(sk.length()>1) {
                    sk.substring(0, sk.length() - 1);
                    jb.setSkill(sk);
                }
                bundles.putParcelable("jb", jb);
                //bundles.putSerializable("cm", cm);
                EditJobFragment ldf = new EditJobFragment ();
                // Bundle args = new Bundle();
                // args.putString("coursename",a);
                ldf.setArguments(bundles);
                mFragmentTransaction.replace(R.id.containerView, ldf);
                mFragmentTransaction.addToBackStack(null);
                mFragmentTransaction.commit();

            }
        });

        arrayAdapter.notifyDataSetChanged();
    return view;
    }


    public String getskill(String job_id)
    {

        final String[] getskill = {""};
        JsonArrayRequest req = new JsonArrayRequest(GETJOBSKILL_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("load jobs", response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            jsonResponse = "";
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject job = (JSONObject) response
                                        .get(i);

                                String name = job.getString("name");
                                getskill[0] = getskill[0] +name+",";
                                                            }

                            //         Toast.makeText(getActivity(), jsonResponse, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("load job", "Error: " + error.getMessage());
                Toast.makeText(getActivity(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);

        return getskill[0];
    }


    public void getjobs()
    {
        JsonArrayRequest req = new JsonArrayRequest(load_jobs,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("load jobs", response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            jsonResponse = "";
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject job = (JSONObject) response
                                        .get(i);

                                String company = job.getString("company_from");
                                String desc = job.getString("description");
                                String vacancies = job.getString("vacancies");
                                String job_id=job.getString("id");
                                String job_title=job.getString("title");
                                String date_of_posting=job.getString("date_of_posting");
                                String last_date=job.getString("last_date");
                                String location=job.getString("location");
                                String category=job.getString("category");
                                String stipend=job.getString("stipend");

                                JobModel model=new JobModel(job_id,company,desc,vacancies,stipend,location,job_title,last_date,date_of_posting);

                                jsonResponse += "Name: " + company + "\n\n";
                                jsonResponse += "Founder: " + desc + "\n\n";
                                jsonResponse += "Category: " + vacancies + "\n\n";
                                data.add(model);
                                arrayAdapter.notifyDataSetChanged();
                            }

                   //         Toast.makeText(getActivity(), jsonResponse, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("load job", "Error: " + error.getMessage());
                Toast.makeText(getActivity(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);

    }


}
