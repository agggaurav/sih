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
 * Created by Gaurav on 28-03-2017.
 */
public class EnrolledCoursesFragment extends Fragment {

    ArrayList<CourseModel> data=new ArrayList<CourseModel>();
    ListView lv;
    public String load_courses =Constants.ip;// "http://192.168.1.101:8000/course/id";
    String jsonResponse;
    CourseAdapter arrayAdapter;
    String user_id;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.enrolled_course_layout, container, false);
        load_courses=Constants.ip;
        user_id="15";
        load_courses=load_courses+"enroll/"+user_id+"/";
        lv=(ListView)view.findViewById(R.id.list_course);
        data=new ArrayList<CourseModel>();
        //data.add("css");
        //data.add("php");
        arrayAdapter =new CourseAdapter(data,getActivity());
        lv.setAdapter(arrayAdapter);
        getCourses();


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {
                //Intent module=new Intent(getActivity(),ModuleFragment.class);
                //startActivity(module);
                mFragmentManager = getActivity().getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();

                Bundle bundles = new Bundle();
                CourseModel cm =(CourseModel) adapter.getItemAtPosition(position);
                bundles.putParcelable("cm", cm);
                //bundles.putSerializable("cm", cm);
                ModulelistFragment ldf = new ModulelistFragment ();
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

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),R.array.Planets, android.R.layout.simple_list_item_1);
        //setListAdapter(adapter);
        //getListView().setOnItemClickListener(this);
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

                                String date = course.getString("enrolled_on");
                                String completion = course.getString("completion");
                                String id = course.getString("course_enrolled");
                                //String description=course.getString("description");

                                CourseModel cm=new CourseModel("fname","name","category");
                                cm.setId(id);
                                cm.setDate(date);
                                data.add(cm);
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
