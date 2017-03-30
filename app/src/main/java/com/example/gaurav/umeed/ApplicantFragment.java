package com.example.gaurav.umeed;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Gaurav on 25-03-2017.
 */
public class ApplicantFragment extends Fragment {

    ListView lv;
    ArrayList<CPModel> data=new ArrayList<CPModel>();
    CPAdapter arrayAdapter;
    TextView applicantName,location,education,app_email,skills,contact;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.applicant_layout, container, false);

        Bundle bundle = getArguments();
        ApplicantModel applicant= (ApplicantModel) bundle.getParcelable("am");

        applicantName=(TextView)view.findViewById(R.id.name);
        location=(TextView)view.findViewById(R.id.location);
        education=(TextView)view.findViewById(R.id.education);
        app_email=(TextView)view.findViewById(R.id.email);
        skills=(TextView)view.findViewById(R.id.skills);
        contact=(TextView)view.findViewById(R.id.contact);
        lv=(ListView)view.findViewById(R.id.courses);

        applicantName.setText(applicant.getApplicantName());
        location.setText(applicant.getLocation());
        education.setText(applicant.getEducation());
        app_email.setText(applicant.getEmail());
        contact.setText(applicant.getContact());
        skills.setText("");
if(applicant.getSkills()!=null) {
    for (int i = 0; i < applicant.getSkills().size(); i++) {
        skills.append(applicant.getSkills().get(i));
    }
}

        data=applicant.getCourses();
if(data!=null) {
    arrayAdapter = new CPAdapter(data, getActivity());
    lv.setAdapter(arrayAdapter);
}
        arrayAdapter.notifyDataSetChanged();
        return view;
    }
    }
