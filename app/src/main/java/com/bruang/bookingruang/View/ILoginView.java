package com.bruang.bookingruang.View;

import android.content.Context;

import com.bruang.bookingruang.Enum.LoginError;

public interface ILoginView {

    void setUsernameError(LoginError error);

    void setPasswordError(LoginError error);

    void onLoginSuccess();

    void onLoginFailed();

    void runOnUiThread(Runnable result);

    Context getApplicationContext();
}
