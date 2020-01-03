package com.bruang.bookingruang.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.bruang.bookingruang.Application.App;
import com.bruang.bookingruang.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.squareup.timessquare.CalendarPickerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SelectDatesActivity extends AppCompatActivity {

    List<Date> selectedDate;
    CalendarPickerView calendarView;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_dates);

        selectedDate = new ArrayList<Date>();
        calendarView = (CalendarPickerView) findViewById(R.id.calendar_view);

        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.MONTH, 6);
        Date today = new Date();

        btnNext = (Button) findViewById(R.id.btn_show_dates);
        btnNext.setOnClickListener(v -> nextAction());

        calendarView.init(today, nextYear.getTime())
                .inMode(CalendarPickerView.SelectionMode.MULTIPLE);

        calendarView.setOnInvalidDateSelectedListener(new CalendarPickerView.OnInvalidDateSelectedListener() {
            @Override
            public void onInvalidDateSelected(Date date) {
                if (selectedDate.size() >= 5)
                    Toast.makeText(SelectDatesActivity.this,
                            getString(R.string.error_maximum_selected_date),
                            Toast.LENGTH_LONG).show();

                if (date.getDay() == 0)
                    Toast.makeText(SelectDatesActivity.this,
                            getString(R.string.error_sunday_selected_date),
                            Toast.LENGTH_LONG).show();
            }
        });
        calendarView.setDateSelectableFilter(new CalendarPickerView.DateSelectableFilter() {
            @Override
            public boolean isDateSelectable(Date date) {
                if (date.getDay() == 0)
                    return false;

                if (selectedDate.contains(date))
                    return true;

                return selectedDate.size() < 5;
            }
        });


        calendarView.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                 selectedDate.add(date);
            }

            @Override
            public void onDateUnselected(Date date) {
                selectedDate.remove(date);
            }
        });
    }

    private void nextAction() {
        List<String> dateList = new ArrayList<>();
        SimpleDateFormat simpleDate =  new SimpleDateFormat("yyyy-MM-dd");

        for (Date date: selectedDate) {
            dateList.add(simpleDate.format((date)));
        }

        Gson gson = new Gson();
        String datesAsJson = gson.toJson(dateList);

        Intent intent = new Intent(this, AvailableRoomListActivity.class);
        intent.putExtra("DateJSON", datesAsJson);
        Log.d("DateAsJSON", datesAsJson);


        startActivity(intent);
    }
}
