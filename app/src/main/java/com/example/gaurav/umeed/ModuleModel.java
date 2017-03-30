package com.example.gaurav.umeed;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Gaurav on 23-03-2017.
 */
public class ModuleModel implements Parcelable {

    String main_course;
    String module_name;
    String module_description;
    String video_path;
    String module_id;
    String date;
    String duration;


    public ModuleModel(String module_id,String main_course,String module_name,String module_description,String video_path)
    {
        this.main_course=main_course;
        this.module_description=module_description;
        this.module_name=module_name;
        this.video_path=video_path;
        this.module_id=module_id;
    }

    protected ModuleModel(Parcel in) {
        main_course = in.readString();
        module_name = in.readString();
        module_description = in.readString();
        video_path = in.readString();
        module_id = in.readString();
    }

    public static final Creator<ModuleModel> CREATOR = new Creator<ModuleModel>() {
        @Override
        public ModuleModel createFromParcel(Parcel in) {
            return new ModuleModel(in);
        }

        @Override
        public ModuleModel[] newArray(int size) {
            return new ModuleModel[size];
        }
    };

    public String getModule_description()
    {
        return module_description;
    }

    public String getModule_name()
    {
        return module_name;
    }
    public String getVideo_path()
    {
        return video_path;
    }
    public String getModule_id(){return module_id;}
    public void setModule_name(String module_name)
    {
        this.module_name=module_name;
    }
    public void setModule_id(String module_id)
    {
        this.module_id=module_id;
    }

    public void setModule_description(String module_description)
    {
        this.module_description=module_description;
    }

    public void setVideo_path(String video_path)
    {
        this.video_path=video_path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(main_course);
        dest.writeString(module_name);
        dest.writeString(module_description);
        dest.writeString(video_path);
        dest.writeString(module_id);
    }
}
