package com.bruang.bookingruang.Presenter;

import com.bruang.bookingruang.Enum.LoginError;
import com.bruang.bookingruang.Model.User;
import com.bruang.bookingruang.View.Login.ILoginView;

public class LoginPresenter {

//    private final String url = "http://10.0.3.2/api/login";
    private ILoginView loginView;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
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

        if (!user.getUsername().equals("admin")) {
            loginView.setUsernameError(LoginError.UsernameWrongError);
            return;
        } else if (!user.getPassword().equals("admin")) {
            loginView.setPasswordError(LoginError.PasswordWrongError);
            return;
        }

        loginView.onLoginSuccess();
    }

}

