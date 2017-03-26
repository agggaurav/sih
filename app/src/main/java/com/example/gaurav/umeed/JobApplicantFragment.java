package com.example.gaurav.umeed;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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
public class JobApplicantFragment extends Fragment {

    ArrayList<ApplicantModel> data=new ArrayList<ApplicantModel>();
    ListView lv;
    public String load_applicants =Constants.ip;// "http://192.168.1.101:8000/loadcourses/";
    String jsonResponse;
    ApplicantAdapter arrayAdapter;

    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.jobapplicant_layout, container, false);

        lv=(ListView)view.findViewById(R.id.applicantlist);
        data=new ArrayList<ApplicantModel>();
        //data.add("css");
        //data.add("php");
        ArrayList<String> interests=new ArrayList<String>();
        interests.add("ml");
        interests.add("big data");
        ArrayList<String> skills=new ArrayList<String>();
        skills.add("python");
        ApplicantModel a=new ApplicantModel("1","Gaurav","Noida",interests,skills);
        data.add(a);
        arrayAdapter =new ApplicantAdapter(data,getActivity());
        lv.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
        //getApplicants();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {
                //Intent module=new Intent(getActivity(),ModuleFragment.class);
                //startActivity(module);
                mFragmentManager = getActivity().getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();

                Bundle bundles = new Bundle();
                ApplicantModel am =(ApplicantModel) adapter.getItemAtPosition(position);
                bundles.putParcelable("cm", am);
                //bundles.putSerializable("cm", cm);
                ApplicantFragment ldf = new ApplicantFragment ();
                // Bundle args = new Bundle();
                // args.putString("coursename",a);
                ldf.setArguments(bundles);
                mFragmentTransaction.replace(R.id.containerView, ldf);
                mFragmentTransaction.addToBackStack(null);
                mFragmentTransaction.commit();
                // assuming string and if you want to get the value on click of list item
                // do what you intend to do on click of listview row
            }
        });

        return view;
    }

    public void getApplicants()
    {

        JsonArrayRequest req = new JsonArrayRequest(load_applicants,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("load courses", response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            jsonResponse = "";
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject course = (JSONObject) response
                                        .get(i);

                                String fname = course.getString("founder");
                                String name = course.getString("name");
                                String category = course.getString("category");

                                jsonResponse += "Name: " + name + "\n\n";
                                jsonResponse += "Founder: " + fname + "\n\n";
                                jsonResponse += "Category: " + category + "\n\n";
                                //ApplicantModel cm=new ApplicantModel("1",fname,name,category);
                                //data.add(cm);
                                arrayAdapter.notifyDataSetChanged();
                            }

                            //Toast.makeText(getActivity(),jsonResponse,Toast.LENGTH_SHORT).show();

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
                VolleyLog.d("load course", "Error: " + error.getMessage());
//                Toast.makeText(getActivity(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);

    }



}
