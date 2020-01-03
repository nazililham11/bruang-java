package com.bruang.bookingruang.Contract;

import android.content.Context;

import com.bruang.bookingruang.Enum.LoginError;
import com.bruang.bookingruang.Model.User;

public interface LoginContract {

    public interface Presenter {
        void onLogin(User user);

        void login(String username, String password);
    }

    public interface View {

        void setUsernameError(LoginError error);

        void setPasswordError(LoginError error);

        void onLoginSuccess();

        void onLoginFailed();

    }

}
