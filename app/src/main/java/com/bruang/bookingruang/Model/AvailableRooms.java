package com.bruang.bookingruang.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AvailableRooms {

    @SerializedName("date")
    @Expose
    String date;

    @SerializedName("schedules")
    @Expose
    List<ClassSchedule> schedules;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ClassSchedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<ClassSchedule> schedules) {
        this.schedules = schedules;
    }


}
