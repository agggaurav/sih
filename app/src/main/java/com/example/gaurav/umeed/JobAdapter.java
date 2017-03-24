package com.example.gaurav.umeed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gaurav on 21-03-2017.
 */
public class JobAdapter extends ArrayAdapter<JobModel> implements View.OnClickListener{
private ArrayList<JobModel> JobSet;
    Context mContext;

// View lookup cache
private static class ViewHolder {
    TextView company;
    TextView desc;
    TextView vacancies;
    //ImageView info;
}

    public JobAdapter(ArrayList<JobModel> Job, Context context) {
        super(context, R.layout.job_row, Job);
        this.JobSet = Job;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        JobModel JobModel=(JobModel)object;

    }

private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the Job item for this position
        JobModel JobModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());

            convertView = inflater.inflate(R.layout.job_row, parent, false);

            viewHolder.company = (TextView) convertView.findViewById(R.id.company);
            viewHolder.desc = (TextView) convertView.findViewById(R.id.desc);
            viewHolder.vacancies = (TextView) convertView.findViewById(R.id.vacancies);
            //viewHolder.info = (ImageView) convertView.findViewById(R.id.item_info);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }
/*
        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
  */
        lastPosition = position;

        viewHolder.company.setText(JobModel.getCompany());
        viewHolder.desc.setText(JobModel.getDesc());
        viewHolder.vacancies.setText(JobModel.getVacancies());
        //viewHolder.info.setOnClickListener(this);
        //viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}