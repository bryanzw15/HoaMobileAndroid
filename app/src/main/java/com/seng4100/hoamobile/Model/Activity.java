package com.seng4100.hoamobile.Model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Activity {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("category")
    @Expose
    private Object category;
    @SerializedName("activitybook_id")
    @Expose
    private String activitybookId;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;
    @SerializedName("tasklists")
    @Expose
    private List<Tasklist> tasklists = new ArrayList<Tasklist>();

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
     * The category
     */
    public Object getCategory() {
        return category;
    }

    /**
     *
     * @param category
     * The category
     */
    public void setCategory(Object category) {
        this.category = category;
    }

    /**
     *
     * @return
     * The activitybookId
     */
    public String getActivitybookId() {
        return activitybookId;
    }

    /**
     *
     * @param activitybookId
     * The activitybook_id
     */
    public void setActivitybookId(String activitybookId) {
        this.activitybookId = activitybookId;
    }

    /**
     *
     * @return
     * The createdAt
     */
    public Object getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     * The created_at
     */
    public void setCreatedAt(Object createdAt) {
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
     * The tasklists
     */
    public List<Tasklist> getTasklists() {
        return tasklists;
    }

    /**
     *
     * @param tasklists
     * The tasklists
     */
    public void setTasklists(List<Tasklist> tasklists) {
        this.tasklists = tasklists;
    }

}
