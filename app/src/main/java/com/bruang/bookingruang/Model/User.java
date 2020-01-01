package com.bruang.bookingruang.Model;

import com.bruang.bookingruang.Enum.LoginError;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("user_id")
    private String user_id;

    @SerializedName("password")
    private String password;

    @SerializedName("name")
    private String name;

    @SerializedName("token")
    private String token;

    public User(String user_id, String password) {
        this.user_id = user_id;
        this.password = password;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LoginError isValid(){

        if (user_id.isEmpty())
            return LoginError.UsernameEmptyError;

        if (user_id.length() < 4)
            return LoginError.UsernameLengthError;

        if (password.isEmpty())
            return LoginError.PasswordEmptyError;

        if (password.length() < 4)
            return LoginError.PasswordLengthError;

        return LoginError.None;
    }

    @Override
    public String toString(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}

