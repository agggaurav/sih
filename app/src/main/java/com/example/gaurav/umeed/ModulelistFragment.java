package com.example.gaurav.umeed;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
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
 * Created by Gaurav on 23-03-2017.
 */
public class ModulelistFragment extends Fragment {

    TextView courseName,founder,category,detail;
    ListView moduleList;
    public String load_modules =Constants.ip; //"http://192.168.1.101:8000/loadmodule/";
    ArrayList<ModuleModel> data=new ArrayList<ModuleModel>();
    ModuleAdapter arrayAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modulelist_layout, container, false);
        load_modules=Constants.ip;
        load_modules=load_modules+"loadmodule/";
        Bundle bundle = getArguments();
        CourseModel course= (CourseModel) bundle.getParcelable("cm");
        courseName=(TextView)view.findViewById(R.id.courseName);
        founder=(TextView)view.findViewById(R.id.founder);
        category=(TextView)view.findViewById(R.id.category);
        detail=(TextView)view.findViewById(R.id.detail);
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
                ModuleModel obj = (ModuleModel)parent.getAdapter().getItem(position);
                Toast.makeText(getActivity(),obj.getVideo_path(),Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getActivity(),ModuleFragment.class);
                i.putExtra("obj",  obj);
                startActivity(i);
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

    }
