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
public class ModuleAdapter extends ArrayAdapter<ModuleModel> implements View.OnClickListener {

private ArrayList<ModuleModel> ModuleSet;
        Context mContext;
// View lookup cache
private static class ViewHolder {
    TextView main_course;
    TextView detail;
    TextView name;
    String video_path;
    //ImageView info;
}

    public ModuleAdapter(ArrayList<ModuleModel> Module, Context context) {
        super(context, R.layout.module_row, Module);
        this.ModuleSet = Module;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        ModuleModel ModuleModel=(ModuleModel)object;

    }

private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the Module item for this position
        ModuleModel ModuleModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());

            convertView = inflater.inflate(R.layout.module_row, parent, false);

            viewHolder.name = (TextView) convertView.findViewById(R.id.moduleName);
         //   viewHolder.main_course = (TextView) convertView.findViewById(R.id.desc);
            viewHolder.detail = (TextView) convertView.findViewById(R.id.moduleDetail);
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

        viewHolder.name.setText(ModuleModel.getModule_name());
        viewHolder.detail.setText(ModuleModel.getModule_description());
        //viewHolder.vacancies.setText(ModuleModel.getVacancies());
        //viewHolder.info.setOnClickListener(this);
        //viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}

