package com.bruang.bookingruang.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bruang.bookingruang.Application.App;
import com.bruang.bookingruang.Model.ClassSchedule;
import com.bruang.bookingruang.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NewBookingDetails extends AppCompatActivity {

    private TextView tvRoom, tvDate, tvTime;
    private EditText etTitle, etCourse;
    private Button btnBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_booking_details);

        bindToView();

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            String scheduleAsJson = (String) bundle.get("ScheduleJSON");
            Log.d("ScheduleJSON", scheduleAsJson);

            ClassSchedule schedule = new Gson().fromJson(scheduleAsJson, ClassSchedule.class);

            if (schedule != null)
                loadToView(schedule);
        }


    }

    private void bindToView() {
        btnBooking = findViewById(R.id.btnBooking);
        tvRoom     = findViewById(R.id.tvRoom);
        tvDate     = findViewById(R.id.tvDate);
        tvTime     = findViewById(R.id.tvTime);
        etTitle    = findViewById(R.id.etTitle);
        etCourse   = findViewById(R.id.etCourse);
    }

    private void loadToView(ClassSchedule schedule) {

        tvRoom.setText(
                String.format("%s%s", App.getResourses().getString(R.string.room_label),
                schedule.getRoom().getFullRoomNumber()));

        tvTime.setText(schedule.getClassSession().getTimeLabel());



    }


}
