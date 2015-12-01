package com.seng4100.hoamobile.API;

import com.seng4100.hoamobile.Model.Activity;
import com.seng4100.hoamobile.Model.Activitybook;
import com.seng4100.hoamobile.Model.Task;
import com.seng4100.hoamobile.Model.Tasklist;
import com.seng4100.hoamobile.Model.User;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface EndpointInterface {

    /**
     * Activitybooks
     */
    @GET("/activitybooks")
    Call<List<Activitybook>> getActivitybooks();
    @GET("/activitybooks/{id}")
    Call<Activitybook> getActivitybook(@Path("id") int id);
    @DELETE("/activitybooks/{id}")
    Call<String> deleteActivitybook(@Path("id") int id);
    @POST("/activitybooks")
    Call<Activitybook> createActivityBook(@Body Activitybook activitybook);



    /**
     * Activities
     */
    @GET("/activities")
    Call<List<Activity>> getActivities();
    @GET("/activities/{id}")
    Call<Activity> getActivity(@Path("id") int id);
    @DELETE("/activities/{id}")
    Call<String> deleteActivity(@Path("id") int id);
    @POST("/activities")
    Call<Activity> createActivity(@Body Activity activity);


    /**
     * Tasklists
     */
    @GET("/tasklists")
    Call<List<Tasklist>> getTasklists();
    @GET("/tasklists/{id}")
    Call<Tasklist> getTasklist(@Path("id") int id);
    @DELETE("/tasklists/{id}")
    Call<String> deleteTasklist(@Path("id") int id);
    @POST("/tasklists")
    Call<Tasklist> createTasklist(@Body Tasklist tasklist);


    /**
     * Tasks
     */
    @GET("/tasks")
    Call<List<Task>> getTasks();
    @GET("/tasks/{id}")
    Call<Task> getTask(@Path("id") int id);
    @DELETE("/tasks/{id}")
    Call<String> deleteTask(@Path("id") int id);
    @POST("/tasks")
    Call<Task> createTask(@Body Task task);

    /**
     * Users
     */
    @POST("/users")
    Call<User> createUser(@Body User user);

}
