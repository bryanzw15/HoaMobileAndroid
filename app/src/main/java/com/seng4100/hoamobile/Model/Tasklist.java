package com.seng4100.hoamobile.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Tasklist {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("completed")
    @Expose
    private String completed;
    @SerializedName("active")
    @Expose
    private String active;
    @SerializedName("activity_id")
    @Expose
    private String activityId;
    @SerializedName("reminder_id")
    @Expose
    private String reminderId;
    @SerializedName("geolocation_id")
    @Expose
    private String geolocationId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;
    @SerializedName("tasks")
    @Expose
    private List<Task> tasks = new ArrayList<>();

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The completed
     */
    public String getCompleted() {
        return completed;
    }

    /**
     *
     * @param completed
     * The completed
     */
    public void setCompleted(String completed) {
        this.completed = completed;
    }

    /**
     *
     * @return
     * The active
     */
    public String getActive() {
        return active;
    }

    /**
     *
     * @param active
     * The active
     */
    public void setActive(String active) {
        this.active = active;
    }

    /**
     *
     * @return
     * The activityId
     */
    public String getActivityId() {
        return activityId;
    }

    /**
     *
     * @param activityId
     * The activity_id
     */
    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    /**
     *
     * @return
     * The reminderId
     */
    public String getReminderId() {
        return reminderId;
    }

    /**
     *
     * @param reminderId
     * The reminder_id
     */
    public void setReminderId(String reminderId) {
        this.reminderId = reminderId;
    }

    /**
     *
     * @return
     * The geolocationId
     */
    public String getGeolocationId() {
        return geolocationId;
    }

    /**
     *
     * @param geolocationId
     * The geolocation_id
     */
    public void setGeolocationId(String geolocationId) {
        this.geolocationId = geolocationId;
    }

    /**
     *
     * @return
     * The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     * The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     *
     * @return
     * The updatedAt
     */
    public Object getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     * @param updatedAt
     * The updated_at
     */
    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     *
     * @return
     * The tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     *
     * @param tasks
     * The tasks
     */
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

}
