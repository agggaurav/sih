package com.example.gaurav.umeed;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Gaurav on 22-03-2017.
 */
public class ModuleFragment  extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    public String load_modules; //"http://192.168.1.101:8000/loadmodule/";
    String jsonResponse;
    ModuleModel model;
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    TextView courseName,moduleName,description;
    // YouTube player view
    private YouTubePlayerView youTubeView;
    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.module_layout);

        Intent i = getIntent();
        model = (ModuleModel)i.getExtras().getParcelable("obj");
        Toast.makeText(this,model.getVideo_path(),Toast.LENGTH_SHORT).show();
      load_modules=Constants.ip;
        load_modules=load_modules+"loadmodule/";
        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        //courseName=(TextView) findViewById(R.id.coursename);
        moduleName=(TextView) findViewById(R.id.modulename);
        description=(TextView) findViewById(R.id.description);
        moduleName.setText(model.getModule_name());
        description.setText(model.getModule_description());
        // Initializing video player with developer key
        youTubeView.initialize(Config.DEVELOPER_KEY, this);
        youTubeView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


    }
});
        // String value = getArguments().getString("coursename");
    }




    public void getmodules()
    {
        JsonArrayRequest req = new JsonArrayRequest(load_modules,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("load modules", response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            jsonResponse = "";
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject module = (JSONObject) response
                                        .get(i);

                                String id = module.getString("id");
                                String video_path = module.getString("video_path");
                                String vacancies = module.getString("vacancies");

                                jsonResponse += "Name: " + id + "\n\n";
                                jsonResponse += "Founder: " + video_path + "\n\n";
                                jsonResponse += "Category: " + vacancies + "\n\n";
                            }

                            Toast.makeText(getApplicationContext(), jsonResponse, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("load module", "Error: " + error.getMessage());
  //              Toast.makeText(getActivity(),
    //                    error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);

    }
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format(
                    getString(R.string.error_player), errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {

            // loadVideo() will auto play video
            // Use cueVideo() method, if you don't want to play it automatically
            player.loadVideo(model.getVideo_path());

            // Hiding player controls
            player.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.DEVELOPER_KEY, this);
        }
    }

    private YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }
}
