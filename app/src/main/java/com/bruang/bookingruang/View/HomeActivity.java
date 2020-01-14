package com.bruang.bookingruang.View;

import android.content.Intent;
import android.os.Bundle;

import com.bruang.bookingruang.Adapter.BookingAdapter;
import com.bruang.bookingruang.Application.App;
import com.bruang.bookingruang.Model.Booking;
import com.bruang.bookingruang.Presenter.HomePresenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.bruang.bookingruang.R;
import com.google.gson.Gson;

import java.util.List;

public class HomeActivity<btnMenu> extends AppCompatActivity implements IHomeView,
        BookingAdapter.ItemClickListener {

    private SwipeRefreshLayout refreshLayout;
    private boolean doubleBackToExitPressedOnce;
    private BookingAdapter adapter;
    private HomePresenter presenter;
    private Button btnNewBooking;
    private Button btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(this::refresh);

        btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(v -> onMenuClick());

        presenter = new HomePresenter(this);

        refresh();
    }
    private void onMenuClick(){
        PopupMenu popup = new PopupMenu(HomeActivity.this, btnMenu);
        popup.getMenuInflater().inflate(R.menu.home_menu, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.newBooking:
                        newBooking();
                        return true;
                    case R.id.refresh:
                        refresh();
                        return true;
                    case R.id.logout:
                        logout();
                        return true;
                    case R.id.exit:
                        finishAffinity();
                        System.exit(0);
                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.show();

    }

    private void refresh() {
        presenter.getBookingList(App.getActiveUser());
    }

    private void logout() {

    }

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            finishAffinity();
            System.exit(0);
            return;
        }

        this.doubleBackToExitPressedOnce = true;

        Toast.makeText(this,
                getString(R.string.back_twice_to_exit_label),
                Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(() -> doubleBackToExitPressedOnce=false, 2000);
    }

    private void newBooking() {
        startActivity(new Intent(this, SelectDatesActivity.class));
        finish();
    }

    @Override
    public void onItemClick(View view, int position) {
        Booking booking = adapter.getItem(position);

        Gson gson = new Gson();
        String bookingAsJson = gson.toJson(booking);

        Intent intent = new Intent(this, BookingDetailsActivity.class);
        intent.putExtra("BookingJSON", bookingAsJson);
        startActivity(intent);

    }

    @Override
    public void loadBookings(List<Booking> bookingList) {
        RecyclerView recyclerView = findViewById(R.id.rvBookings);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new BookingAdapter(this, bookingList);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        refreshLayout.setRefreshing(false);
    }
}