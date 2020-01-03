package com.bruang.bookingruang.View;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bruang.bookingruang.Enum.BookingStatus;
import com.bruang.bookingruang.Model.Booking;
import com.bruang.bookingruang.R;
import com.google.gson.Gson;

public class BookingDetailsActivity extends AppCompatActivity {

    private TextView tvTitle, tvLongDate, tvRoom, tvStatus, tvDate, tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);

        bindToView();

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            String bookingAsJson = (String) bundle.get("BookingJSON");

            Booking booking = new Gson().fromJson(bookingAsJson, Booking.class);

            if (booking != null)
                showBooking(booking);
        }
    }

    private void bindToView(){
        tvTitle    = findViewById(R.id.tvTitle);
        tvLongDate = findViewById(R.id.tvLongDate);
        tvRoom     = findViewById(R.id.tvRoom);
        tvStatus   = findViewById(R.id.tvStatus);
//        tvDate     = findViewById(R.id.tvDate);
        tvTime     = findViewById(R.id.tvTime);
    }

    private void showBooking(Booking booking) {
        if (booking == null)
            return;
        BookingStatus status = BookingStatus.getBookingStatus(booking.getStatus());

        tvTitle.setText(booking.getTitle());
        tvLongDate.setText(booking.getFormatedLongDate());
        tvRoom.setText(booking.getClassSchedule().getRoom().getFullRoomNumber());
        tvStatus.setText(booking.getStatus());
        tvStatus.setTextColor(status.getStatusColor());
//        tvDate.setText(booking.getFormatedShortDate());
        tvTime.setText(booking.getClassSchedule().getClassSession().getTimeLabel());

    }
}
