package com.example.gaurav.umeed;

import android.os.Parcelable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Tutor extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    public String jobtrend =Constants.ip;// "http://192.168.1.101:8000/course/id";
    String jsonResponse;
    ArrayList<String> categories=new ArrayList<>();
    ArrayList<String> val=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor);

        /**
         *Setup the DrawerLayout and NavigationView
         */

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff) ;

        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView,new TutorTabFragment()).commit();
        /**
         * Setup click events on the Navigation View Items.
         */

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

                if (menuItem.getItemId() == R.id.nav_item_home) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView,new TutorTabFragment()).commit();

                }

                if (menuItem.getItemId() == R.id.nav_item_profile) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView,new TutorProfileFragment());
                    xfragmentTransaction.addToBackStack(null);
                    xfragmentTransaction.commit();
                }

                if(menuItem.getItemId() == R.id.nav_item_courses) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView,new CourseFragment());
                    xfragmentTransaction.addToBackStack(null);
                    xfragmentTransaction.commit();
                }


                if(menuItem.getItemId() == R.id.nav_item_evaluation) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView,new TutorEvaluationFragment());
                    xfragmentTransaction.addToBackStack(null);
                    xfragmentTransaction.commit();
                }

                if(menuItem.getItemId() == R.id.nav_item_student_analysis) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView,new StudentAnalysisFragment());
                    xfragmentTransaction.addToBackStack(null);
                    xfragmentTransaction.commit();
                }


                if(menuItem.getItemId() == R.id.nav_item_job_analysis) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();

                    xfragmentTransaction.replace(R.id.containerView,new JobAnalysisFragment());
                    xfragmentTransaction.addToBackStack(null);
                    xfragmentTransaction.commit();
                }

                if(menuItem.getItemId() == R.id.nav_item_edit_courses) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();

                    xfragmentTransaction.replace(R.id.containerView,new EditCourseFragment());
                    xfragmentTransaction.addToBackStack(null);
                    xfragmentTransaction.commit();
                }


                return false;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

    }



}