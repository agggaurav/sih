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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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
 * Created by Gaurav on 26-03-2017.
 */
public class TutorEvaluationFragment extends Fragment {
    Spinner courseList,moduleList;
    ArrayList<String> courses;
    ArrayList<String> modules;
    public String load_courses =Constants.ip;// "http://192.168.1.101:8000/course/id";
    public String load_modules =Constants.ip;// "http://192.168.1.101:8000/course/id";

    String jsonResponse;
    ArrayAdapter<String> arrayAdapter;
    ArrayAdapter<String> moduleAdapter;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    Button add;
String c_id,m_id;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tutorevaluation_layout, container, false);
        load_courses=Constants.ip;
        load_courses=load_courses+"loadcourses/";
        load_modules=Constants.ip;
        load_modules=load_modules+"coursemodule/";
        add=(Button)view.findViewById(R.id.addeval);
        courseList=(Spinner)view.findViewById(R.id.courselist);
        moduleList=(Spinner)view.findViewById(R.id.modulelist);
        courses=new ArrayList<String>();
        modules=new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, courses);
        courseList.setAdapter(arrayAdapter);
        moduleAdapter= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, modules);
moduleList.setAdapter(moduleAdapter);
        getCourses();

        courseList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String cm = parent.getItemAtPosition(position).toString();
                c_id = cm;
                load_modules = Constants.ip+"coursemodule/";
                load_modules = load_modules + cm + "/";
                moduleAdapter.clear();
                getModules();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                String cm = parent.getItemAtPosition(0).toString();
                c_id = cm;
            }
        });


        moduleList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                m_id= parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
               // m_id= parent.getItemAtPosition(0).toString();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentManager = getActivity().getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();
                Bundle bundles = new Bundle();
                bundles.putString("course_id",c_id);
                bundles.putString("module_id",m_id);
                AddEvaluation ldf = new AddEvaluation ();
                // Bundle args = new Bundle();
                // args.putString("coursename",a);
                ldf.setArguments(bundles);
                mFragmentTransaction.replace(R.id.containerView, ldf);
                mFragmentTransaction.commit();
            }
        });
        return view;
    }

    public void getCourses()
    {
        JsonArrayRequest req = new JsonArrayRequest(load_courses,
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
                                String id=course.getString("id");

                                jsonResponse += "Name: " + name + "\n\n";
                                //jsonResponse += "Founder: " + fname + "\n\n";
                                jsonResponse += "Category: " + category + "\n\n";

                                courses.add(id);
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


    public void getModules()
    {
        JsonArrayRequest req = new JsonArrayRequest(load_modules,
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

                                String id=course.getString("id");

                                modules.add(id);
                                moduleAdapter.notifyDataSetChanged();
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
