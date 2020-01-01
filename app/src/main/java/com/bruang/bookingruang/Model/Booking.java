package com.bruang.bookingruang.Model;

import android.util.Log;

import com.bruang.bookingruang.Enum.BookingStatus;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("user_id")
    @Expose
    private Integer userId;

    @SerializedName("course_id")
    @Expose
    private Integer courseId;

    @SerializedName("class_schedule_id")
    @Expose
    private Integer classScheduleId;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("day")
    @Expose
    private Integer day;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @SerializedName("class_schedule")
    @Expose
    private ClassSchedule classSchedule;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getClassScheduleId() {
        return classScheduleId;
    }

    public void setClassScheduleId(Integer classScheduleId) {
        this.classScheduleId = classScheduleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public ClassSchedule getClassSchedule() {
        return classSchedule;
    }

    public void setClassSchedule(ClassSchedule classSchedule) {
        this.classSchedule = classSchedule;
    }

    /**Convert String to Date with Default Format is yyyy MM dd*/
    public Date stringToDateConverter(String stringDate){
        return stringToDateConverter(stringDate, "yyyy MM dd");
    }
    /**Convert String to Date As Format*/
    public Date stringToDateConverter(String stringDate, String format){
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try {
            Date date = formatter.parse(stringDate);
            return date;
        } catch (Exception ex) {
        }

        return new Date(1995,1, 1);
    }

    /**Get Formated Short Date Ex: "17 November 2020"*/
    public String getFormatedShortDate(){
        return DateFormat.getDateInstance(DateFormat.MEDIUM)
                .format(stringToDateConverter(this.date))
                .toString();
    }

    /**Get Formated Long Date Ex: Saturday, "17 November 2020"*/
    public String getFormatedLongDate(){
        return DateFormat.getDateInstance(DateFormat.FULL)
                .format(stringToDateConverter(this.date))
                .toString();
    }

}
