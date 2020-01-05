package com.bruang.bookingruang.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bruang.bookingruang.Application.App;
import com.bruang.bookingruang.POJO.UserDetailResponse;
import com.bruang.bookingruang.R;
import com.bruang.bookingruang.Rest.ApiClient;
import com.bruang.bookingruang.Rest.ApiInterface;
import com.bruang.bookingruang.Util.UserPreference;

public class SplashActivity extends AppCompatActivity {

    private ApiInterface mApiInterface;
    private UserPreference preference;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressBar = findViewById(R.id.pbSplash);

        preference = new UserPreference(this);

        if (TextUtils.isEmpty(preference.getUserToken())){
            navigateToLogin();
        } else {
            mApiInterface = ApiClient.createService(ApiInterface.class, preference.getUserToken());
            getUserDetails();
        }
    }

    private void getUserDetails() {
        retrofit2.Call<UserDetailResponse> userDetailCall = mApiInterface.getUserDetails();

        userDetailCall.enqueue(new retrofit2.Callback<UserDetailResponse>() {
            @Override
            public void onResponse(@NonNull retrofit2.Call<UserDetailResponse> call,
                                   @NonNull retrofit2.Response<UserDetailResponse> response) {
                Log.d("Response", response.body().toString());
                if (response.isSuccessful()){
                    Log.d("Response ", String.valueOf(response.isSuccessful()));

                    if (TextUtils.equals(response.body().getStatus(), "success")){
                        Log.d("D/Response", response.body().getStatus());
                        App.setActiveUser(response.body().getUser());
                        onGetDetailsSuccess();
                        return;
                    } else if (response.body().getStatus().equals("error")){
                        if (!TextUtils.isEmpty(response.body().getMessage()))
                            onGetDetailsFailed(response.body().getMessage());
                    }
                } else {
                    Log.d("Response", "Failed...!!");
                    onGetDetailsFailed();
                }

            }

            @Override
            public void onFailure(retrofit2.Call<UserDetailResponse> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                onGetDetailsFailed();
            }
        });
    }


    private void onGetDetailsSuccess() {
        progressBar.setVisibility(View.GONE);
        navigateHome();
    }
    private void onGetDetailsFailed() {
        onGetDetailsFailed("Error Occured");
        progressBar.setVisibility(View.GONE);
    }
    private void onGetDetailsFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG);
        progressBar.setVisibility(View.GONE);
        navigateToLogin();
    }

    private void navigateToLogin(){
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void navigateHome(){
        Log.e("D/Intent", "Go to Home");
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }
}
