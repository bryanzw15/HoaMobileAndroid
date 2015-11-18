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

    @GET("/activitybooks")
    Call<Activitybooks> getActivitybooks();
    @GET("/activitybooks/{id}")
    Call<Activitybook> getActivitybook(@Path("id") int id);

    @GET("/activities")
    Call<Activities> getActivities();
    @GET("/activities/{id}")
    Call<Activity> getActivity(@Path("id") int id);

    @GET("/tasklists")
    Call<List<Tasklist>> getTasklists();
    @GET("/tasklists/{id}")
    Call<Tasklist> getTasklist(@Path("id") int id);

    @GET("/tasks")
    Call<List<Task>> getTasks();
    @GET("/tasks/{id}")
    Call<Task> getTask(@Path("id") int id);
}
