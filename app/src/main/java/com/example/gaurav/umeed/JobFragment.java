package com.example.gaurav.umeed;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
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
public class JobFragment extends Fragment {

    ArrayList<JobModel> data=new ArrayList<JobModel>();
    ListView lv;
    EditText searchjob;
    Button searchbtn;
    public String load_jobs =Constants.ip; //"http://192.168.1.101:8000/loadjobs/";
    String jsonResponse;
    JobAdapter arrayAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.social_layout, container, false);
        load_jobs=Constants.ip;
        load_jobs=load_jobs+"jobs/0/";
        lv=(ListView)view.findViewById(R.id.list_job);
        searchjob=(EditText)view.findViewById(R.id.search_job);
        searchbtn=(Button)view.findViewById(R.id.jobbtn);
        data=new ArrayList<JobModel>();
        arrayAdapter =new JobAdapter(data,getActivity());
        lv.setAdapter(arrayAdapter);
        getjobs();
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayAdapter.notifyDataSetChanged();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                JobModel obj=(JobModel)parent.getAdapter().getItem(position);
                final Dialog dialog = new Dialog(getActivity());
                // Include dialog.xml file
                dialog.setContentView(R.layout.job_dialog);
                // Set dialog title
                dialog.setTitle(obj.getTitle());
                TextView companyName=(TextView) dialog.findViewById(R.id.companyName);
                TextView companyAddress=(TextView) dialog.findViewById(R.id.companyAddress);
                TextView category=(TextView) dialog.findViewById(R.id.category);
                TextView companyIntro=(TextView) dialog.findViewById(R.id.companyIntro);
                TextView jobDesc=(TextView) dialog.findViewById(R.id.jobDesc);
                TextView vacancies=(TextView) dialog.findViewById(R.id.vacancies);
                TextView last_date=(TextView) dialog.findViewById(R.id.last_date);
                companyName.setText(obj.getCompany());
                companyAddress.setText(obj.getLocation());
                jobDesc.setText(obj.getDesc());
                vacancies.setText(obj.getVacancies());
                last_date.setText(obj.getLast_date());
                Button apply=(Button) dialog.findViewById(R.id.applyjob);
                dialog.show();
                apply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

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
                                String title=job.getString("title");
                                String date_of_posting=job.getString("date_of_posting");
                                String last_date=job.getString("last_date");
                                String location=job.getString("location");
                                String stipend=job.getString("stipend");
                                String job_id=job.getString("id");

                                JobModel model=new JobModel(job_id,company,desc,vacancies,stipend,location,title,last_date,date_of_posting);

                                jsonResponse += "Name: " + company + "\n\n";
                                jsonResponse += "Founder: " + desc + "\n\n";
                                jsonResponse += "Category: " + vacancies + "\n\n";
                                data.add(model);
                                arrayAdapter.notifyDataSetChanged();
                            }

                            Toast.makeText(getActivity(), jsonResponse, Toast.LENGTH_SHORT).show();
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
            //    Toast.makeText(getActivity(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);

    }

}
