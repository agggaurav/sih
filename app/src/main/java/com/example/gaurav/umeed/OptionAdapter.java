package com.example.gaurav.umeed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Gaurav on 31-03-2017.
 */
public class OptionAdapter extends ArrayAdapter<OptionModel> implements View.OnClickListener {
    private ArrayList<OptionModel> OptionSet;
    Context mContext;
    // View lookup cache
    private static class ViewHolder {
        CheckBox opt;
    }

    public OptionAdapter(ArrayList<OptionModel> Option, Context context) {
        super(context, R.layout.option_row, Option);
        this.OptionSet = Option;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);

            }

    private int lastPosition = -1;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the Course item for this position
        String value = getItem(position).getOption();
        OptionModel opm=(OptionModel) getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        final ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());

            convertView = inflater.inflate(R.layout.option_row, parent, false);

            viewHolder.opt = (CheckBox) convertView.findViewById(R.id.opt);
            //viewHolder.desc = (TextView) convertView.findViewById(R.id.desc);
            //viewHolder.vacancies = (TextView) convertView.findViewById(R.id.vacancies);
            //viewHolder.info = (ImageView) convertView.findViewById(R.id.item_info);

            result=convertView;

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }
/*
        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
  */
        lastPosition = position;

        viewHolder.opt.setText(value);

        viewHolder.opt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptionModel nm=(OptionModel) getItem(position);
                if(nm.getValue()==1) {
                 viewHolder.opt.setChecked(false);
                    nm.setValue(0);
                }
                else if(nm.getValue()==0)
                {
                    viewHolder.opt.setChecked(true);
                    nm.setValue(1);
                }
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }
}

