package com.example.gaurav.umeed;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * Created by Gaurav on 25-03-2017.
 */
public class CPAdapter extends ArrayAdapter<CPModel> implements View.OnClickListener {

    private ArrayList<CPModel> CPSet;
    Context mContext;

    private LineGraphSeries<DataPoint> mSeries1;
    GraphView gf;


    // View lookup cache
    private static class ViewHolder {
        GraphView graph;
        TextView name;
        HashMap<String,Double> a;
        //ImageView info;
    }

    public CPAdapter(ArrayList<CPModel> CP, Context context) {
        super(context, R.layout.cp_row, CP);
        this.CPSet = CP;
        this.mContext = context;

    }

    @Override
    public void onClick(View v) {

        int position = (Integer) v.getTag();
        Object object = getItem(position);
        CPModel cpModel = (CPModel) object;

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the CP item for this position
        CPModel CPModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());

            convertView = inflater.inflate(R.layout.cp_row, parent, false);

            viewHolder.graph = (GraphView) convertView.findViewById(R.id.graph);
            viewHolder.name = (TextView) convertView.findViewById(R.id.cpName);
            //viewHolder.desc = (TextView) convertView.findViewById(R.id.desc);
            //viewHolder.vacancies = (TextView) convertView.findViewById(R.id.vacancies);
            //viewHolder.info = (ImageView) convertView.findViewById(R.id.item_info);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }
/*
        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
  */
        lastPosition = position;
        HashMap<String,Double> data1=new HashMap<>();
        //data1=viewHolder.a;
        viewHolder.name.setText(CPModel.getCourseName());
        data1=CPModel.getModelEval();
if(data1!=null) {
    Toast.makeText(mContext, data1.toString(), Toast.LENGTH_SHORT).show();
    mSeries1 = new LineGraphSeries<>(generateData(data1));

    viewHolder.graph.getViewport().setXAxisBoundsManual(true);
    viewHolder.graph.getViewport().setMinX(0);
    viewHolder.graph.getViewport().setMaxX(data1.size());
    viewHolder.graph.getViewport().setScalable(true);
    viewHolder.graph.getViewport().setScalableY(true);
    viewHolder.graph.addSeries(mSeries1);
}
        // Return the completed view to render on screen
        return convertView;
    }

    private DataPoint[] generateData(HashMap<String,Double> data) {
        int count = 30;
        DataPoint[] values = new DataPoint[data.size()];
        Iterator myVeryOwnIterator = data.keySet().iterator();
        int i=0;
        while(myVeryOwnIterator.hasNext()) {
            String x =(String)myVeryOwnIterator.next();

            double y = data.get(x);

            DataPoint v = new DataPoint(Double.valueOf(i),y);
            values[i] = v;
            i++;
        }
        Toast.makeText(mContext,values.toString(),Toast.LENGTH_SHORT).show();
        return values;
    }

}



