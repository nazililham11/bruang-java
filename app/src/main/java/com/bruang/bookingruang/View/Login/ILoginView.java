package com.bruang.bookingruang.View.Login;

import com.bruang.bookingruang.Enum.LoginError;

public interface ILoginView {

    void setUsernameError(LoginError error);

    void setPasswordError(LoginError error);

    void onLoginSuccess();

}
