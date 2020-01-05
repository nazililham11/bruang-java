package com.bruang.bookingruang.Enum;

import android.graphics.Color;
import android.text.TextUtils;

import androidx.core.content.ContextCompat;

import com.bruang.bookingruang.Application.App;
import com.bruang.bookingruang.R;

public enum BookingStatus {
    Accepted("Accepted"),
    Rejected("Rejected"),
    Pending("Pending");

    private final String bookingStatus;

    BookingStatus(String bookingStatus){
        this.bookingStatus = bookingStatus;
    }

    public static String getStatusLabel(BookingStatus status){
        switch (status){
            case Accepted:
                return App.getResourses().getString(R.string.booking_status_accepted_label);
            case Pending:
                return App.getResourses().getString(R.string.booking_status_pending_label);
            default:
                return App.getResourses().getString(R.string.booking_status_rejected_label);
        }
    }

    public static BookingStatus getBookingStatus(String status){

        if (TextUtils.equals(status.toLowerCase(), "accepted"))
            return BookingStatus.Accepted;

        if (TextUtils.equals(status.toLowerCase(), "pending"))
            return BookingStatus.Pending;

        return BookingStatus.Rejected;
    }

    /**Get Status As int Color*/
    public int getStatusColor(){

        switch (this){
            case Pending:
                return Color.YELLOW;
            case Accepted:
                return Color.GREEN;
            case Rejected:
                return Color.RED;
            default:
                return Color.WHITE;
        }
    }

    /**Convert BookingStatus Enum to String*/
    public String toString(){
        return this.bookingStatus;
    }
}
