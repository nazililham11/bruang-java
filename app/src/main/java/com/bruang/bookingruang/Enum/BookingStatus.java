package com.bruang.bookingruang.Enum;

import android.graphics.Color;
import android.text.TextUtils;

public enum BookingStatus {
    Accepted("Accepted"),
    Rejected("Rejected"),
    Pending("Pending");

    private final String bookingStatus;

    BookingStatus(String bookingStatus){
        this.bookingStatus = bookingStatus;
    }

    public static BookingStatus getBookingStatus(String status){
        if (status == null)
            return Rejected;

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
