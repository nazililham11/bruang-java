package com.bruang.bookingruang.Rest;

import com.bruang.bookingruang.Model.Room;
import com.bruang.bookingruang.POJO.BookingsResponse;
import com.bruang.bookingruang.POJO.CreateBookingResponse;
import com.bruang.bookingruang.POJO.LoginResponse;
import com.bruang.bookingruang.POJO.RoomsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> postLogin(@Field("user_id") String user_id,
                                  @Field("password") String password);


    @Headers({
        "Accept: application/json",
        "Content-Type: application/x-www-form-urlencoded",
        "User-Agent: BRUANG"
    })
    @GET("bookings")
    Call<BookingsResponse> getBookings(@Header("Authorization") String authorization);


    @FormUrlEncoded
    @POST("rooms")
    Call<RoomsResponse> postRooms(@Field("dates[]") List<String> dates);


    @FormUrlEncoded
    @POST("createbooking")
    Call<CreateBookingResponse> postBooking(@Field("class_schedule_id") int class_schedule_id,
                                            @Field("title") String title,
                                            @Field("date") String date,
                                            @Field("day") int day);

}