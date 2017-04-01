package com.example.gaurav.umeed;

/**
 * Created by Gaurav on 31-03-2017.
 */
public class OptionModel {
    String option;
    int value;

    public OptionModel(String option)
    {
     this.option=option;
        value=0;
    }

    public int getValue() {
        return value;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
