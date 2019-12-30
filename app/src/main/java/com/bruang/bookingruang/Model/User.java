package com.bruang.bookingruang.Model;

import com.bruang.bookingruang.Enum.LoginError;

public class User {

    private String username, password;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginError isValid(){

        if (username.isEmpty())
            return LoginError.UsernameEmptyError;

        if (username.length() < 4)
            return LoginError.UsernameLengthError;

        if (password.isEmpty())
            return LoginError.PasswordEmptyError;

        if (password.length() < 4)
            return LoginError.PasswordLengthError;

        return LoginError.None;
    }

}

