package com.bruang.bookingruang.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Time;

public class ClassSession {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("start_hour")
    @Expose
    private Integer startHour;

    @SerializedName("start_minutes")
    @Expose
    private Integer startMinutes;

    @SerializedName("end_hour")
    @Expose
    private Integer endHour;

    @SerializedName("end_minutes")
    @Expose
    private Integer endMinutes;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStartHour() {
        return startHour;
    }

    public void setStartHour(Integer startHour) {
        this.startHour = startHour;
    }

    public Integer getStartMinutes() {
        return startMinutes;
    }

    public void setStartMinutes(Integer startMinutes) {
        this.startMinutes = startMinutes;
    }

    public Integer getEndHour() {
        return endHour;
    }

    public void setEndHour(Integer endHour) {
        this.endHour = endHour;
    }

    public Integer getEndMinutes() {
        return endMinutes;
    }

    public void setEndMinutes(Integer endMinutes) {
        this.endMinutes = endMinutes;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }


    /**Get Full Time Label As String Ex: "07:00-08:40"*/
    public String getTimeLabel(){
        return String.format("%02d:%02d-%02d:%02d", startHour, startMinutes, endHour, endMinutes);
    }

}
