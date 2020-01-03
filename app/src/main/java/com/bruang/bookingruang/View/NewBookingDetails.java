package com.bruang.bookingruang.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bruang.bookingruang.Application.App;
import com.bruang.bookingruang.Model.ClassSchedule;
import com.bruang.bookingruang.POJO.CreateBookingResponse;
import com.bruang.bookingruang.R;
import com.bruang.bookingruang.Rest.ApiClient;
import com.bruang.bookingruang.Rest.ApiInterface;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NewBookingDetails extends AppCompatActivity {

    private TextView tvRoom, tvDate, tvTime;
    private EditText etTitle;
    private Button btnBooking;
    private ClassSchedule schedule;
    private ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_booking_details);
        mApiInterface = ApiClient.createService(ApiInterface.class, App.getActiveUser().getToken());

        bindToView();

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            String scheduleAsJson = (String) bundle.get("ScheduleJSON");
            Log.d("ScheduleJSON", scheduleAsJson);

            this.schedule = new Gson().fromJson(scheduleAsJson, ClassSchedule.class);

            if (this.schedule != null)
                loadToView(this.schedule);
        }

        btnBooking.setOnClickListener(v -> onBooking(this.schedule, etTitle.getText().toString()));

    }

    private void bindToView() {
        btnBooking = findViewById(R.id.btnBooking);
        tvRoom     = findViewById(R.id.tvRoom);
        tvDate     = findViewById(R.id.tvDate);
        tvTime     = findViewById(R.id.tvTime);
        etTitle    = findViewById(R.id.etTitle);
    }

    private void loadToView(ClassSchedule schedule) {

        tvRoom.setText(
                String.format("%s %s", App.getResourses().getString(R.string.room_label),
                schedule.getRoom().getFullRoomNumber()));

        tvTime.setText(schedule.getClassSession().getTimeLabel());

    }

    private void onBooking(ClassSchedule schedule, String title) {
        int id = (int) schedule.getId();
        int day = (int) schedule.getDay();
        Log.d("OnBooking", "Booking");
        retrofit2.Call<CreateBookingResponse> bookingCall =
                mApiInterface.postBooking(id,
                                          title,
                                          schedule.getDate(),
                                          day);

        bookingCall.enqueue(new retrofit2.Callback<CreateBookingResponse>() {
            @Override
            public void onResponse(@NonNull retrofit2.Call<CreateBookingResponse> call,
                                   @NonNull retrofit2.Response<CreateBookingResponse> response) {
                Log.d("OnBooking", "Booking1");

                if (response.isSuccessful()){
                    Log.d("OnBooking", "Booking2");

                    if (TextUtils.equals(response.body().getStatus(), "success")){
                        Log.d("OnBooking", "Booking3");

                        onBookingSuccess();
                        return;

                    } else {
                        String message = TextUtils.isEmpty(response.body().getMessage()) ? "" :
                                response.body().getMessage();

                        onBookingFailed();
                    }
                } else {
                    Log.d("Response", "Failed...!!");
                }
                onBookingFailed();
            }

            @Override
            public void onFailure(retrofit2.Call<CreateBookingResponse> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                onBookingFailed();
            }
        });
    }

    private void onBookingSuccess(){
        startActivity(new Intent(this, HomeActivity.class));

    }

    private void onBookingFailed(){

    }
}
