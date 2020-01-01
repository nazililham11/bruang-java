package com.bruang.bookingruang.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bruang.bookingruang.Enum.LoginError;
import com.bruang.bookingruang.Model.User;
import com.bruang.bookingruang.Presenter.LoginPresenter;
import com.bruang.bookingruang.R;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    private Button btnLogin;
    private TextView tvUsernameError, tvPasswordError;
    private EditText etUsername, etPassword;
    private LoginPresenter loginPresenter;
    private boolean doubleBackToExitPressedOnce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindToView();

        loginPresenter = new LoginPresenter(this);
        btnLogin.setOnClickListener(v -> onLogin());

    }

    private void bindToView() {
        btnLogin   = findViewById(R.id.btnLogin);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        tvUsernameError = findViewById(R.id.tvUsernameError);
        tvPasswordError = findViewById(R.id.tvPasswordError);
    }

    private void onLogin(){
        btnLogin.setEnabled(false);

        setUsernameError(LoginError.None);
        setPasswordError(LoginError.None);

        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString();

        loginPresenter.onLogin(new User(username, password));

    }

    public void navigateToHome() {
        startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            finishAffinity(); // Close all activites
            System.exit(0);  // Releasing resources
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(() -> doubleBackToExitPressedOnce=false, 2000);
    }

    @Override
    public void onLoginSuccess() {
        btnLogin.setEnabled(true);
        navigateToHome();
        etPassword.setText("");
    }

    @Override
    public void onLoginFailed() {
        btnLogin.setEnabled(true);
        etPassword.setText("");
    }

    @Override
    public void setUsernameError(LoginError error){
        String errorMsg;

        switch (error){
            case UsernameWrongError:
                errorMsg = getString(R.string.error_username_wrong);
                break;
            case UsernameEmptyError:
                errorMsg = getString(R.string.error_username_empty);
                break;
            case UsernameLengthError:
                errorMsg = getString(R.string.error_username_length);
                break;
            default:
                errorMsg = "";
        }

        tvUsernameError.setText(errorMsg);
        tvUsernameError.setVisibility(!error.equals(LoginError.None) ? View.VISIBLE : View.GONE);

    }

    @Override
    public void setPasswordError(LoginError error){

        String errorMsg;

        switch (error){
            case None:
                errorMsg = "";
                break;
            case PasswordWrongError:
                errorMsg = getString(R.string.error_password_wrong);
                break;
            case PasswordEmptyError:
                errorMsg = getString(R.string.error_password_empty);
                break;
            case PasswordLengthError:
                errorMsg = getString(R.string.error_password_length);
                break;
            default:
                errorMsg = "";
        }

        tvPasswordError.setText(errorMsg);
        tvPasswordError.setVisibility(!error.equals(LoginError.None) ? View.VISIBLE : View.GONE);

    }

}
