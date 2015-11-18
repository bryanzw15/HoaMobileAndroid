package com.seng4100.hoamobile.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Activities {
    @SerializedName("activities")
    @Expose
    private List<Activity> activities = new ArrayList<Activity>();

    /**
     *
     * @return
     * The activities
     */
    public List<Activity> getActivities() {
        return activities;
    }

    /**
     *
     * @param activities
     * The activities
     */
    public void setActivitybooks(List<Activity> activities) {
        this.activities = activities;
    }
}
