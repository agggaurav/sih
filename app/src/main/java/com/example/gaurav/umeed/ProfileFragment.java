package com.example.gaurav.umeed;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
 * Created by Ratan on 7/29/2015.
 */
public class ProfileFragment extends Fragment {

    EditText userName,dob,address,contact,email,skills;
    public String load_profiles = Constants.ip;//"http://192.168.1.101:8000/profile/gaurav23dec@gmail.com/";
    String jsonResponse;
    ListView lv;
    ArrayList<CPModel> data=new ArrayList<CPModel>();
    CPAdapter arrayAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sent_layout, container, false);
        load_profiles=load_profiles+"profile/";
        String user_id="2";
        load_profiles=load_profiles+user_id+"/";
        userName=(EditText)view.findViewById(R.id.profle_name);
        dob=(EditText)view.findViewById(R.id.profle_age);
        address=(EditText)view.findViewById(R.id.profle_address);
        contact=(EditText)view.findViewById(R.id.profle_contact);
        email=(EditText)view.findViewById(R.id.profile_email);
        skills=(EditText)view.findViewById(R.id.skill_gained);
        lv=(ListView)view.findViewById(R.id.enrolled_courses);
        data=new ArrayList<CPModel>();
        arrayAdapter =new CPAdapter(data,getActivity());
        lv.setAdapter(arrayAdapter);
        getprofile();


        return view;

    }

    private void getprofile()
    {
        final String[] skill_acquired = {""};
        JsonArrayRequest req = new JsonArrayRequest(load_profiles,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("load profiles", response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            jsonResponse = "";
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject profile = (JSONObject) response
                                        .get(i);

                                String name = profile.getString("name");
                                String addr = profile.getString("address");
                                String phone = profile.getString("phone");
                                String email_id=profile.getString("email");
                                String age=profile.getString("dob");
                                JSONArray courses=profile.getJSONArray("Enrolled in courses");
                                for(int j=0;j<courses.length();j++)
                                {
                                    JSONObject cs=(JSONObject)courses.get(j);
                                    String course_id=cs.getString("id");
                                    String enrolled_on=cs.getString("enrolled_on");
                                    String completion=cs.getString("completion");
                                    String user_id=cs.getString("id");
                                    CPModel cp=new CPModel();
                                    cp.setCourseId(course_id);
                                    cp.setEnrolled_on(enrolled_on);
                                    cp.setCompletion(completion);
                                    data.add(cp);
                                    arrayAdapter.notifyDataSetChanged();
                                }
                                JSONArray skill_gained=profile.getJSONArray("Skill Gained");
                                for (int k=0;k<skill_gained.length();k++)
                                {
                                    JSONObject sg=(JSONObject)skill_gained.get(k);
                                    String skill_name=sg.getString("name");
                                    skill_acquired[0] = skill_acquired[0] +","+skill_name;
                                }
                                if(name==null){
                                    Toast.makeText(getActivity(), "NULL", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                userName.setText(name);
                                dob.setText(age);
                                address.setText(addr);
                                email.setText(email_id);
                                contact.setText(phone);
                                skills.setText(skill_acquired[0]);
                                }
                                jsonResponse += "Name: " + name + "\n\n";
                                jsonResponse += "Founder: " + address + "\n\n";
                                jsonResponse += "Category: " + dob + "\n\n";
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
                VolleyLog.d("load profile", "Error: " + error.getMessage());
                Toast.makeText(getActivity(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);

    }


}
