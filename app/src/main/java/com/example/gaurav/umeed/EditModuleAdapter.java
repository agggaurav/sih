package com.example.gaurav.umeed;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gaurav on 27-03-2017.
 */
public class EditModuleAdapter extends ArrayAdapter<ModuleModel> implements View.OnClickListener {

    private ArrayList<ModuleModel> ModuleSet;
    Context mContext;
    // View lookup cache
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    private static class ViewHolder {
        EditText video;
        EditText desc;
        EditText name;
        Button submit;
        //ImageView info;
    }

    public EditModuleAdapter(ArrayList<ModuleModel> Module, Context context) {
        super(context, R.layout.editmodule_layout, Module);
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
        // Get the Course item for this position
        final ModuleModel ModuleModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());

            convertView = inflater.inflate(R.layout.editmodule_layout, parent, false);

            viewHolder.name = (EditText) convertView.findViewById(R.id.moduleName);
            viewHolder.desc = (EditText) convertView.findViewById(R.id.moduleDesc);
            viewHolder.video = (EditText) convertView.findViewById(R.id.moduleVideo);
            viewHolder.submit = (Button) convertView.findViewById(R.id.addques);

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
        viewHolder.desc.setText(ModuleModel.getModule_description());
        viewHolder.video.setText(ModuleModel.getVideo_path());

        viewHolder.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentManager =((AppCompatActivity) mContext).getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();

                Bundle bundles = new Bundle();
                bundles.putParcelable("cm", ModuleModel);
                AddEvaluation ldf = new AddEvaluation ();
                ldf.setArguments(bundles);
                mFragmentTransaction.replace(R.id.containerView, ldf);
                mFragmentTransaction.addToBackStack(null);
                mFragmentTransaction.commit();

            }
        });
        //viewHolder.vacancies.setText(CourseModel.getVacancies());
        //viewHolder.info.setOnClickListener(this);
        //viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}


