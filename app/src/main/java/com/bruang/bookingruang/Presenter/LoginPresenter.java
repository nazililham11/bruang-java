package com.bruang.bookingruang.Presenter;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.bruang.bookingruang.Application.App;
import com.bruang.bookingruang.Enum.LoginError;
import com.bruang.bookingruang.Model.User;
import com.bruang.bookingruang.POJO.LoginResponse;
import com.bruang.bookingruang.Rest.ApiClient;
import com.bruang.bookingruang.Rest.ApiInterface;
import com.bruang.bookingruang.View.ILoginView;

public class LoginPresenter {

    private ILoginView loginView;
    private static final String preferenceName = "AppPref";
    private ApiInterface mApiInterface;

    //Shared Preferences
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;

        pref = loginView
                .getApplicationContext()
                .getSharedPreferences(preferenceName, 0);

        editor = pref.edit();

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

    }

    public void onLogin(User user) {

        LoginError error = user.isValid();

        if (loginView == null)
            return;

        switch (error){
            case UsernameEmptyError:
            case UsernameLengthError:
            case UsernameWrongError:
                loginView.setUsernameError(error);
                return;
            case PasswordEmptyError:
            case PasswordLengthError:
            case PasswordWrongError:
                loginView.setPasswordError(error);
                return;
        }
        login(user.getUser_id(), user.getPassword());
//        loginView.onLoginSuccess();
    }


    public void login(String username, String password) {

        retrofit2.Call<LoginResponse> loginCall = mApiInterface.postLogin(username, password);

        loginCall.enqueue(new retrofit2.Callback<LoginResponse>() {
            @Override
            public void onResponse(retrofit2.Call<LoginResponse> call, retrofit2.Response<LoginResponse>
                    response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus().equals("success")){
                        User user = response.body().getUser();
                        App.setActiveUser(user);
                        String msg = "Welcome " + user.getName();
                        Log.d("Response", msg);
                        Toast.makeText(App.getInstance().getApplicationContext(), msg, Toast.LENGTH_LONG);
                        loginView.onLoginSuccess();
                    } else {
                        String message = response.body().getMessage();
                        if (!TextUtils.isEmpty(message)) {
                            Toast.makeText(App.getInstance().getApplicationContext(), message, Toast.LENGTH_LONG);
                        }
                        loginView.onLoginFailed();
                    }
                } else {
                    Log.d("Response", "Failed...!!");
                    loginView.onLoginFailed();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<LoginResponse> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                loginView.onLoginFailed();
            }
        });
    }


}

