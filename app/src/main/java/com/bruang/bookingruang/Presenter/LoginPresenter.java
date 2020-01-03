package com.bruang.bookingruang.Presenter;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bruang.bookingruang.Application.App;
import com.bruang.bookingruang.Contract.LoginContract;
import com.bruang.bookingruang.Enum.LoginError;
import com.bruang.bookingruang.Model.User;
import com.bruang.bookingruang.POJO.LoginResponse;
import com.bruang.bookingruang.Rest.ApiClient;
import com.bruang.bookingruang.Rest.ApiInterface;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View loginView;
    private ApiInterface mApiInterface;

    public LoginPresenter(LoginContract.View loginView) {
        this.loginView = loginView;
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

    }

    public void onLogin(User user) {

        LoginError error = user.isValid();

        if (loginView == null)
            return;

        switch (error){
            case UsernameEmptyError:
            case UsernameLengthError:
                loginView.setUsernameError(error);
                return;
            case PasswordEmptyError:
            case PasswordLengthError:
                loginView.setPasswordError(error);
                return;
        }

        login(user.getUser_id(), user.getPassword());
    }

    public void login(String username, String password) {

        retrofit2.Call<LoginResponse> loginCall = mApiInterface.postLogin(username, password);

        loginCall.enqueue(new retrofit2.Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull retrofit2.Call<LoginResponse> call,
                                   @NonNull retrofit2.Response<LoginResponse> response) {

                if (response.isSuccessful()){
                    if (response.body().getStatus().equals("success")){
                        App.setActiveUser(response.body().getUser());
                        loginView.onLoginSuccess();
                        return;

                    } else {
                        String message = response.body().getMessage();

                        if (TextUtils.equals(message, "user_id"))
                            loginView.setUsernameError(LoginError.UsernameWrongError);
                        else if (TextUtils.equals(message, "password"));
                            loginView.setPasswordError(LoginError.UsernameWrongError);

                    }
                } else {
                    Log.d("Response", "Failed...!!");
                }
                loginView.onLoginFailed();
            }

            @Override
            public void onFailure(retrofit2.Call<LoginResponse> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                loginView.onLoginFailed();
            }
        });
    }


}

