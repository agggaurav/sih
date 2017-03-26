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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Gaurav on 25-03-2017.
 */
public class JobFormFragment extends Fragment {

    EditText jobDesc,jobLoc,jobVacancies,jobStipend;
    TextView jobSkills;
    Button submit;
    Button addskill;
    EditText skillset;
    public static final String REGISTER_URL = "http://simplifiedcoding.16mb.com/UserRegistration/volleyRegister.php";
    ArrayList<String> values =new  ArrayList<String>();
    ArrayAdapter<String> mArrayAdapter;
    Boolean checked;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.jobform_layout, container, false);
        jobDesc=(EditText)view.findViewById(R.id.description);
        jobLoc=(EditText)view.findViewById(R.id.location);
        jobVacancies=(EditText)view.findViewById(R.id.vacancies);
        jobStipend=(EditText)view.findViewById(R.id.stipend);
        jobSkills=(TextView)view.findViewById(R.id.skills_require);
        submit=(Button)view.findViewById(R.id.create_job);
        skillset=(EditText)view.findViewById(R.id.skillset);
        addskill=(Button)view.findViewById(R.id.addskill);

        values.add("css");
        values.add("php");
        values.add("c++");
        addskill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getActivity());
                // Include dialog.xml file
                dialog.setContentView(R.layout.skill_dialog);
               skillset.setText("");
                ArrayList<String> skills = new ArrayList<String>();

                final String[] selectedskill = {""};
                mArrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_multiple_choice, android.R.id.text1, values);

                final ListView[] skilllist = {(ListView) dialog.findViewById(R.id.list_skill_dialog)};

                skilllist[0].setAdapter(mArrayAdapter);

                Button submitskill=(Button)dialog.findViewById(R.id.skill_dialog_btn);

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
                        selectedskill[0]=str;

                    }
                });

                submitskill.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        skillset.setText(selectedskill[0]);
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String desc=jobDesc.getText().toString();
                String loc=jobLoc.getText().toString();
                String vacancies=jobVacancies.getText().toString();
                String stipend=jobStipend.getText().toString();
                String skill=jobSkills.getText().toString();
                createjob(desc,loc,vacancies,stipend,skill);
            }
        });
        return view;
    }


public void createjob(final String desc, final String loc, final String vacancies, final String stipend, final String skill)
{
    StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getActivity(),response,Toast.LENGTH_LONG).show();
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
            params.put("skill",skill);
            params.put("stipend",stipend);
            params.put("location",loc);
            return params;
        }

    };

    RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
    requestQueue.add(stringRequest);
}

}





