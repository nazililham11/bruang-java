package com.bruang.bookingruang.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bruang.bookingruang.Adapter.AvailableRoomAdapter;
import com.bruang.bookingruang.Application.App;
import com.bruang.bookingruang.Model.Booking;
import com.bruang.bookingruang.Model.ClassSchedule;
import com.bruang.bookingruang.POJO.BookingsResponse;
import com.bruang.bookingruang.POJO.RoomsResponse;
import com.bruang.bookingruang.R;
import com.bruang.bookingruang.Rest.ApiClient;
import com.bruang.bookingruang.Rest.ApiInterface;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AvailableRoomListActivity extends AppCompatActivity
        implements AvailableRoomAdapter.ItemClickListener {

    private ApiInterface mApiInterface;
    private AvailableRoomAdapter adapter;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_room_list);

        mApiInterface = ApiClient.createService(ApiInterface.class, App.getActiveUser().getToken());

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            String roomsAsJson = (String) bundle.get("DateJSON");
            Log.d("roomAsJSON", roomsAsJson);

            Type listType = new TypeToken<ArrayList<String>>(){}.getType();
            List<String> rooms = new Gson().fromJson(roomsAsJson, listType);

            if (rooms != null)
                getRoomsList(rooms);
        }

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> onBackPressed());

    }


    public void loadRooms(List<ClassSchedule> roomList) {
        RecyclerView recyclerView = findViewById(R.id.rvAvailableRooms);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new AvailableRoomAdapter(this, roomList);
        adapter.setClickListener(this);

        recyclerView.setAdapter(adapter);
    }


    public void getRoomsList(List<String> dates) {

        if (dates == null)
            return;

//        String authorization = "Bearer " + App.getActiveUser().getToken();
        retrofit2.Call<RoomsResponse> roomsCall = mApiInterface.postRooms(dates);

        roomsCall.enqueue(new retrofit2.Callback<RoomsResponse>() {
            @Override
            public void onResponse(retrofit2.Call<RoomsResponse> call,
                                   retrofit2.Response<RoomsResponse> response) {

                if (response.isSuccessful()){
                    Log.d("Response", response.body().toString());
                    if (TextUtils.equals(response.body().getStatus(), "success")){
                        Log.d("Response", response.body().getMessage());
                        loadRooms(response.body().getClassSchedules());
                    } else {
                        String message = response.body().getMessage();
                        if (!TextUtils.isEmpty(message)) {
//                            Toast.makeText(App.getInstance().getApplicationContext(), message,
//                                    Toast.LENGTH_LONG).show();
                            Log.d("Response Msg", response.body().getMessage());

                        }
                    }
                } else {
                    Log.d("Response", "Failed...!!");
                }
            }

            @Override
            public void onFailure(retrofit2.Call<RoomsResponse> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
//        Toast.makeText(this,
//                String.format("You clicked %s on row number %d",
//                        String.format("%s At %s", adapter.getItem(position).getRoom().getFullRoomNumber(), adapter.getItem(position).getClassSession().getTimeLabel()),
//                        position),Toast.LENGTH_SHORT).show();

        ClassSchedule schedule = adapter.getItem(position);

        Gson gson = new Gson();
        String scheduleAsJson = gson.toJson(schedule);

        Intent intent = new Intent(this, NewBookingDetails.class);
        intent.putExtra("ScheduleJSON", scheduleAsJson);

        Log.d("GET Schedule", "Completed");
        startActivity(intent);

    }
}
