package com.example.gaurav.umeed;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Highlight;
import com.github.mikephil.charting.utils.PercentFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gaurav on 26-03-2017.
 */
public class JobAnalysisFragment extends Fragment {

    public String jobtrend =Constants.ip;// "http://192.168.1.101:8000/course/id";
    String jsonResponse;
    PieChart pieChart;
    ArrayList<Entry> yvalues;
    ArrayList<String> xVals;
    ArrayList<String> cat_count;
    public int total_count=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.jobanalysis_layout, container, false);
        jobtrend=Constants.ip;
        jobtrend=jobtrend+"jobtrend/";

        xVals = new ArrayList<String>();
        cat_count=new ArrayList<String>();
        yvalues = new ArrayList<Entry>();


        getCourses(view);




/*
        yvalues.add(new Entry(8f, 0));
        yvalues.add(new Entry(15f, 1));
        yvalues.add(new Entry(12f, 2));
        yvalues.add(new Entry(25f, 3));
        yvalues.add(new Entry(23f, 4));
        yvalues.add(new Entry(17f, 5));
*/


/*
        xVals.add("January");
        xVals.add("February");
        xVals.add("March");
        xVals.add("April");
        xVals.add("May");
        xVals.add("June");
*/       // Toast.makeText(getActivity(),"PASSDFF"+cat,Toast.LENGTH_SHORT).show();

        return view;
    }


    public void getCourses(final View view)
    {
        JsonArrayRequest req = new JsonArrayRequest(jobtrend,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("load courses", response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            jsonResponse = "";
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject course = (JSONObject) response
                                        .get(i);

                                String category = course.getString("category");
                                String count=course.getString("count");
                                xVals.add(category);
                                cat_count.add(count);
                                Toast.makeText(getActivity(),"Category"+category,Toast.LENGTH_SHORT).show();

        if(i==response.length()-1)
        {
            plot(view);
        }

                            }

                            //

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
                VolleyLog.d("load course", "Error: " + error.getMessage());
//                Toast.makeText(getActivity(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
//Toast.makeText(getActivity(),"dffff"+cat,Toast.LENGTH_SHORT).show();
    }


    public void plot(View view)
    {
        pieChart = (PieChart) view.findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);
        //pieChart.notifyDataSetChanged();
        //pieChart.invalidate();

        int total=0;

        for (int i=0;i<cat_count.size();i++)
        {
            total=total+Integer.parseInt(cat_count.get(i));
        }

        Toast.makeText(getActivity(),String.valueOf(total),Toast.LENGTH_SHORT).show();

        for(int j=0;j<cat_count.size();j++)
        {
            Float b=Float.parseFloat(cat_count.get(j));
            Toast.makeText(getActivity(),String.valueOf(b),Toast.LENGTH_SHORT).show();
            b=b/total;
            Toast.makeText(getActivity(),String.valueOf(b),Toast.LENGTH_SHORT).show();
            b=b*100;
            Toast.makeText(getActivity(),String.valueOf(b),Toast.LENGTH_SHORT).show();
            //Float a= Float.valueOf((Integer.parseInt(cat_count.get(j))/total)*100);
            //Toast.makeText(getActivity(),String.valueOf(a),Toast.LENGTH_SHORT).show();

            yvalues.add(new Entry(b, j));
        }

        PieDataSet dataSet = new PieDataSet(yvalues, "INDUSTRY");


        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());


        pieChart.notifyDataSetChanged(); // let the chart know it's data changed
        pieChart.invalidate();

        pieChart.setData(data);

        pieChart.notifyDataSetChanged(); // let the chart know it's data changed
        pieChart.invalidate();

        Toast.makeText(getActivity(),String.valueOf(yvalues.size()),Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(),String.valueOf(xVals.size()),Toast.LENGTH_SHORT).show();

        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        data.setValueTextSize(15f);
        pieChart.notifyDataSetChanged(); // let the chart know it's data changed
        pieChart.invalidate();

    }

}
