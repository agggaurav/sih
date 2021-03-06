package com.example.gaurav.umeed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gaurav on 23-03-2017.
 */
public class CourseAdapter extends ArrayAdapter<CourseModel> implements View.OnClickListener {

    private ArrayList<CourseModel> CourseSet;
    Context mContext;
    // View lookup cache
    private static class ViewHolder {
        TextView founder;
        TextView category;
        TextView name;
        //ImageView info;
    }

    public CourseAdapter(ArrayList<CourseModel> Course, Context context) {
        super(context, R.layout.course_row, Course);
        this.CourseSet = Course;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        CourseModel CourseModel=(CourseModel)object;

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the Course item for this position
        CourseModel CourseModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());

            convertView = inflater.inflate(R.layout.course_row, parent, false);

            viewHolder.name = (TextView) convertView.findViewById(R.id.coursename);
            //viewHolder.desc = (TextView) convertView.findViewById(R.id.desc);
            //viewHolder.vacancies = (TextView) convertView.findViewById(R.id.vacancies);
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

        viewHolder.name.setText(CourseModel.getName());
       // viewHolder.desc.setText(CourseModel.getDesc());
        //viewHolder.vacancies.setText(CourseModel.getVacancies());
        //viewHolder.info.setOnClickListener(this);
        //viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}

