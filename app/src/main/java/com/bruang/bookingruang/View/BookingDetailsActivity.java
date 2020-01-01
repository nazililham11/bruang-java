package com.bruang.bookingruang.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.bruang.bookingruang.Enum.BookingStatus;
import com.bruang.bookingruang.Model.Booking;
import com.bruang.bookingruang.Model.Room;
import com.bruang.bookingruang.R;
import com.google.gson.Gson;

public class BookingDetailsActivity extends AppCompatActivity {

    TextView tvTitle, tvLongDate, tvRoom, tvStatus, tvDate, tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);

        tvTitle    = (TextView) findViewById(R.id.tvTitle);
        tvLongDate = (TextView) findViewById(R.id.tvLongDate);
        tvRoom     = (TextView) findViewById(R.id.tvRoom);
        tvStatus   = (TextView) findViewById(R.id.tvStatus);
        tvDate     = (TextView) findViewById(R.id.tvDate);
        tvTime     = (TextView) findViewById(R.id.tvTime);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            String bookingAsJson = (String) bundle.get("BookingJSON");

            Booking booking = new Gson().fromJson(bookingAsJson, Booking.class);

            if (booking != null)
                showBooking(booking);
        }
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
        tvDate.setText(booking.getFormatedShortDate());
        tvTime.setText(booking.getClassSchedule().getClassSession().getTimeLabel());

    }
}
