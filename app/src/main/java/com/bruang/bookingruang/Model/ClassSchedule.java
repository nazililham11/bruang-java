package com.bruang.bookingruang.Model;

import android.text.format.DateUtils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.DayOfWeek;
import java.util.Date;

public class ClassSchedule {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("room_id")
    @Expose
    private Integer roomId;

    @SerializedName("class_session_id")
    @Expose
    private Integer classSessionId;

    @SerializedName("day")
    @Expose
    private Integer day;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @SerializedName("room")
    @Expose
    private Room room;

    @SerializedName("class_session")
    @Expose
    private ClassSession classSession;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getClassSessionId() {
        return classSessionId;
    }

    public void setClassSessionId(Integer classSessionId) {
        this.classSessionId = classSessionId;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public ClassSession getClassSession() {
        return classSession;
    }

    public void setClassSession(ClassSession classSession) {
        this.classSession = classSession;
    }

}
