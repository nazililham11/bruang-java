package com.bruang.bookingruang.View;

import android.content.Intent;
import android.os.Bundle;

import com.bruang.bookingruang.Adapter.BookingAdapter;
import com.bruang.bookingruang.Application.App;
import com.bruang.bookingruang.Model.Booking;
import com.bruang.bookingruang.Model.Room;
import com.bruang.bookingruang.Presenter.HomePresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bruang.bookingruang.R;
import com.google.gson.Gson;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements IHomeView,
        BookingAdapter.ItemClickListener {

    private BookingAdapter adapter;
    private HomePresenter presenter;
    private Button btnNewBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FloatingActionButton fab = findViewById(R.id.add);
        fab.setOnClickListener(v -> newBooking());
//        {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        presenter = new HomePresenter(this);
        presenter.getBookingList(App.getActiveUser());
    }

    private void newBooking() {
//        startActivity(new Intent(this, NewBookingFilterActivity.class));
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this,
                String.format("You clicked %s on row number %d",
                        adapter.getItem(position).getTitle(),
                        position),
                Toast.LENGTH_SHORT).show();

        Booking booking = adapter.getItem(position);

        Gson gson = new Gson();
        String bookingAsJson = gson.toJson(booking);

        Intent intent = new Intent(this, BookingDetailsActivity.class);
        intent.putExtra("BookingJSON", bookingAsJson);

        Log.d("GEt Booking", "Completed");
        startActivity(intent);

    }

    @Override
    public void loadBookings(List<Booking> bookingList) {
        RecyclerView recyclerView = findViewById(R.id.rvBookings);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new BookingAdapter(this, bookingList);
        adapter.setClickListener(this);

        recyclerView.setAdapter(adapter);
    }
}
