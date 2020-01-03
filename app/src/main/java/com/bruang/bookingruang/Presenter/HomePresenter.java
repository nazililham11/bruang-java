package com.bruang.bookingruang.Presenter;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.bruang.bookingruang.Application.App;
import com.bruang.bookingruang.Model.Booking;
import com.bruang.bookingruang.Model.User;
import com.bruang.bookingruang.POJO.BookingsResponse;
import com.bruang.bookingruang.Rest.ApiClient;
import com.bruang.bookingruang.Rest.ApiInterface;
import com.bruang.bookingruang.View.IHomeView;

import java.util.List;

public class HomePresenter {

    private IHomeView homeView;
    private ApiInterface mApiInterface;

    public HomePresenter(IHomeView homeView) {
        this.homeView = homeView;
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

    }
    public void getBookingList(User user) {

        if (user == null)
            return;

        String authorization = "Bearer " + user.getToken();

        retrofit2.Call<BookingsResponse> bookingCall = mApiInterface.getBookings(authorization);

        bookingCall.enqueue(new retrofit2.Callback<BookingsResponse>() {
            @Override
            public void onResponse(retrofit2.Call<BookingsResponse> call, retrofit2.Response<BookingsResponse>
                    response) {

                if (response.isSuccessful()){
                    if (response.body().getStatus().equals("success")){
                        List<Booking> bookingList = response.body().getBookings();
                        homeView.loadBookings(bookingList);
                    } else {
                        String message = response.body().getMessage();
                        if (!TextUtils.isEmpty(message)) {
//                            Toast.makeText(App.getInstance().getApplicationContext(), message,
//                                    Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Log.d("Response", "Failed...!!");
                }
            }

            @Override
            public void onFailure(retrofit2.Call<BookingsResponse> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }



}
