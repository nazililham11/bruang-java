package com.bruang.bookingruang.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.bruang.bookingruang.Application.App;
import com.bruang.bookingruang.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    public class loadUrl extends AsyncTask<String, String, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {

//            FetchJson js = new FetchJson(SplashActivity.this);
//            boolean res = js.getConnection();
//
//            if (js.checkVersion() && res) {
//                js.saveResto();
//                js.saveCats();
//                js.saveContent();
//            }
//
//            return res;
            return false;

        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            if (!result)
                Toast.makeText(SplashActivity.this,
                        App.getResourses().getString(R.string.error_no_internet_connection),
                        Toast.LENGTH_LONG).show();

            finish();

            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);

        }
    }

}
