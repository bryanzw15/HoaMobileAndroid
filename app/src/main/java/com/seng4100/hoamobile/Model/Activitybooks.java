package com.seng4100.hoamobile.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Activitybooks {

    @SerializedName("activitybooks")
    @Expose
    private List<Activitybook> activitybooks = new ArrayList<>();

    /**
     *
     * @return
     * The activitybooks
     */
    public List<Activitybook> getActivitybooks() {
        return activitybooks;
    }

    /**
     *
     * @param activitybooks
     * The activitybooks
     */
    public void setActivitybooks(List<Activitybook> activitybooks) {
        this.activitybooks = activitybooks;
    }

}
