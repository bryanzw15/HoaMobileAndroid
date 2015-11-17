package com.seng4100.hoamobile.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Task {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("status")
    @Expose
    private Object status;
    @SerializedName("tasklist_id")
    @Expose
    private String tasklistId;
    @SerializedName("creation")
    @Expose
    private Object creation;
    @SerializedName("reminder_id")
    @Expose
    private String reminderId;
    @SerializedName("geolocation_id")
    @Expose
    private String geolocationId;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;
    @SerializedName("tasklist")
    @Expose
    private Tasklist tasklist;

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The status
     */
    public Object getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(Object status) {
        this.status = status;
    }

    /**
     * @return The tasklistId
     */
    public String getTasklistId() {
        return tasklistId;
    }

    /**
     * @param tasklistId The tasklist_id
     */
    public void setTasklistId(String tasklistId) {
        this.tasklistId = tasklistId;
    }

    /**
     * @return The creation
     */
    public Object getCreation() {
        return creation;
    }

    /**
     * @param creation The creation
     */
    public void setCreation(Object creation) {
        this.creation = creation;
    }

    /**
     * @return The reminderId
     */
    public String getReminderId() {
        return reminderId;
    }

    /**
     * @param reminderId The reminder_id
     */
    public void setReminderId(String reminderId) {
        this.reminderId = reminderId;
    }

    /**
     * @return The geolocationId
     */
    public String getGeolocationId() {
        return geolocationId;
    }

    /**
     * @param geolocationId The geolocation_id
     */
    public void setGeolocationId(String geolocationId) {
        this.geolocationId = geolocationId;
    }

    /**
     * @return The createdAt
     */
    public Object getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt The created_at
     */
    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return The updatedAt
     */
    public Object getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt The updated_at
     */
    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @return The tasklist
     */
    public Tasklist getTasklist() {
        return tasklist;
    }

    /**
     * @param tasklist The tasklist
     */
    public void setTasklist(Tasklist tasklist) {
        this.tasklist = tasklist;
    }
}