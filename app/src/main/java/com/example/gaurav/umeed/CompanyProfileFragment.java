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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gaurav on 25-03-2017.
 */
public class CompanyProfileFragment extends Fragment {

    EditText name,founder,address,email,desc,contact,emp_count,category;
    public String load_profiles = Constants.ip;//"http://192.168.1.101:8000/profile/gaurav23dec@gmail.com/";
    String jsonResponse;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.company_profile_layout, container, false);
        load_profiles=load_profiles+"companyprofile/";
        String company_id="2";
        load_profiles=load_profiles+company_id+"/";
        name=(EditText)view.findViewById(R.id.name);
        desc=(EditText)view.findViewById(R.id.intro);
        address=(EditText)view.findViewById(R.id.address);
        contact=(EditText)view.findViewById(R.id.mobile);
        email=(EditText)view.findViewById(R.id.email);
        founder=(EditText)view.findViewById(R.id.founder);
        emp_count=(EditText)view.findViewById(R.id.emp_count);
        category=(EditText)view.findViewById(R.id.category);
        getprofile();


        return view;

    }

    private void getprofile()
    {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                load_profiles, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            // Parsing json array response
                            // loop through each json object
                            jsonResponse = "";


                                JSONObject profile = (JSONObject) jsonObject;

                                name.setText(profile.getString("company_name"));
                                address.setText(profile.getString("company_address"));
                                contact.setText(profile.getString("mobile"));
                                email.setText(profile.getString("email"));
                                founder.setText(profile.getString("founder"));
                                desc.setText(profile.getString("company_introduction"));
                                emp_count.setText(profile.getString("employee_count"));
                                category.setText(profile.getString("category"));


                                jsonResponse += "Name: " + name + "\n\n";
                                jsonResponse += "Founder: " + address + "\n\n";




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
        AppController.getInstance().addToRequestQueue(jsonObjReq);

    }


}
