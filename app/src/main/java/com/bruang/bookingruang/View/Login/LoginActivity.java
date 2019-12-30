package com.bruang.bookingruang.View.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bruang.bookingruang.Enum.LoginError;
import com.bruang.bookingruang.Model.User;
import com.bruang.bookingruang.Presenter.LoginPresenter;
import com.bruang.bookingruang.R;
import com.bruang.bookingruang.View.Home.HomeActivity;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    Button button;
    TextView tvUsernameError, tvPasswordError;
    EditText etUsername, etPassword;
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button     = findViewById(R.id.btnLogin);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        tvUsernameError = findViewById(R.id.tvUsernameError);
        tvPasswordError = findViewById(R.id.tvPasswordError);

        loginPresenter = new LoginPresenter(this);

        button.setOnClickListener(v -> proceedLogin());

    }

    private void proceedLogin(){

        setUsernameError(LoginError.None);
        setPasswordError(LoginError.None);

        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString();

        etPassword.setText("");
        loginPresenter.onLogin(new User(username, password));

    }

    public void navigateToHome() {
        startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    public void onLoginSuccess() {
        navigateToHome();
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
