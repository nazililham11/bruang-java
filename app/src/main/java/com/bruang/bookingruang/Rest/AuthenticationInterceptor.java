package com.bruang.bookingruang.Rest;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticationInterceptor implements Interceptor {

    private String authToken;

    public AuthenticationInterceptor(String token) {
        this.authToken = token;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder()
                .header("Authorization", "Bearer " + authToken);
        builder.addHeader("Accept","application/json");
        builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
        builder.addHeader("User-Agent", "BRUANG");
        Request request = builder.build();
        return chain.proceed(request);
    }
}