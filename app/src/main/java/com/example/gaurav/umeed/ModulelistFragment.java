package com.example.gaurav.umeed;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gaurav on 23-03-2017.
 */
public class ModulelistFragment extends Fragment {

    TextView courseName,founder,category,detail;
    ListView moduleList;
    public String load_modules =Constants.ip; //"http://192.168.1.101:8000/loadmodule/";
    ArrayList<ModuleModel> data=new ArrayList<ModuleModel>();
    ModuleAdapter arrayAdapter;
    Button enroll;
    String ENROLL_URL="";
    String course_id;
    String user_id;
    String completion= "0";
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modulelist_layout, container, false);
        ENROLL_URL=Constants.ip;
        user_id="15";
        ENROLL_URL=ENROLL_URL+"enroll/"+user_id+"/";
        load_modules=Constants.ip;
        load_modules=load_modules+"loadmodule/";
        Bundle bundle = getArguments();
        CourseModel course= (CourseModel) bundle.getParcelable("cm");
        course_id=course.getId();
        course_id="2";
        courseName=(TextView)view.findViewById(R.id.courseName);
        founder=(TextView)view.findViewById(R.id.founder);
        category=(TextView)view.findViewById(R.id.category);
        detail=(TextView)view.findViewById(R.id.detail);
        enroll=(Button)view.findViewById(R.id.enroll);
        load_modules=load_modules+course.getName()+"/";
        courseName.setText(course.getName());
        founder.setText(course.getFounder());
        category.setText(course.getCategory());
        if(course.getDetail()!=null)
            detail.setText(course.getDetail());
        data=new ArrayList<ModuleModel>();
        moduleList=(ListView)view.findViewById(R.id.modulelist);
        arrayAdapter =new ModuleAdapter(data,getActivity());
        moduleList.setAdapter(arrayAdapter);
        getmodules();

        moduleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ModuleModel obj = (ModuleModel) parent.getAdapter().getItem(position);
                Toast.makeText(getActivity(), obj.getVideo_path(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), ModuleFragment.class);
                i.putExtra("obj", obj);
                startActivity(i);
            }
        });


        enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enrolluser();
                mFragmentManager = getActivity().getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();
                CourseFragment ldf = new CourseFragment ();
                // Bundle args = new Bundle();
                // args.putString("coursename",a);

                mFragmentTransaction.replace(R.id.containerView, ldf);

                mFragmentTransaction.commit();
            }
        });
        return view;


    }

String jsonResponse;

    public void getmodules()
    {
        JsonArrayRequest req = new JsonArrayRequest(load_modules,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("load modules", response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            jsonResponse = "";
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject module = (JSONObject) response
                                        .get(i);

                                String id = module.getString("id");
                                String video_path = module.getString("video_path");
                                String main_course = module.getString("main_course");
                                String module_name=module.getString("module_name");
                                String module_description=module.getString("module_description");

                                ModuleModel model=new ModuleModel(id,main_course,module_name,module_description,video_path);
                                data.add(model);
                                arrayAdapter.notifyDataSetChanged();
                                jsonResponse += "Name: " + id + "\n\n";
                                jsonResponse += "Founder: " + video_path + "\n\n";
                                jsonResponse += "Category: " + module_description + "\n\n";
                            }

                            //Toast.makeText(getActivity(), jsonResponse, Toast.LENGTH_SHORT).show();
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
                VolleyLog.d("load module", "Error: " + error.getMessage());
                //              Toast.makeText(getActivity(),
                //                    error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);

    }

    public void enrolluser(){


        StringRequest stringRequest = new StringRequest(Request.Method.POST, ENROLL_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(getActivity().getApplicationContext(),response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity().getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("course_enrolled",course_id);
                params.put("user",user_id);
                params.put("completion",completion);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    }
