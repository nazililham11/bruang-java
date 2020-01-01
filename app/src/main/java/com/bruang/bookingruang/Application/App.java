package com.bruang.bookingruang.Application;

import android.app.Application;
import android.content.res.Resources;

import com.bruang.bookingruang.Model.User;
import com.bruang.bookingruang.Rest.ApiInterface;

public class App extends Application {

    private static App mInstance;
    private static Resources res;

    private static User activeUser;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        res = getResources();
    }

    public static App getInstance() {
        return mInstance;
    }

    public static Resources getResourses() {
        return res;
    }

    public static User getActiveUser() {
        return activeUser;
    }

    public static void setActiveUser(User activeUser) {
        App.activeUser = activeUser;
    }

}