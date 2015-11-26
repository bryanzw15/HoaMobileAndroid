package com.seng4100.hoamobile.API;


import com.seng4100.hoamobile.Model.Activities;
import com.seng4100.hoamobile.Model.Activity;
import com.seng4100.hoamobile.Model.Activitybook;
import com.seng4100.hoamobile.Model.Activitybooks;
import com.seng4100.hoamobile.Model.Task;
import com.seng4100.hoamobile.Model.Tasklist;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

public interface EndpointInterface {

    /**
     * Activitybooks
     */
    @GET("/activitybooks")
    Call<List<Activitybook>> getActivitybooks();
    @GET("/activitybooks/{id}")
    Call<Activitybook> getActivitybook(@Path("id") int id);



    /**
     * Activities
     */
    @GET("/activities")
    Call<List<Activity>> getActivities();
    @GET("/activities/{id}")
    Call<Activity> getActivity(@Path("id") int id);


    /**
     * Tasklists
     */
    @GET("/tasklists")
    Call<List<Tasklist>> getTasklists();
    @GET("/tasklists/{id}")
    Call<Tasklist> getTasklist(@Path("id") int id);


    /**
     * Tasks
     */
    @GET("/tasks")
    Call<List<Task>> getTasks();
    @GET("/tasks/{id}")
    Call<Task> getTask(@Path("id") int id);
}
