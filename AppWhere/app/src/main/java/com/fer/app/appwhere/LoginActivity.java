package com.fer.app.appwhere;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.fer.app.appwhere.model.ResponseObj;
import com.fer.app.appwhere.remote.ApiUtils;
import com.fer.app.appwhere.remote.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

     EditText email,password;
     ImageButton login;
     UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        login = (ImageButton)findViewById(R.id.login);
        userService = ApiUtils.getUserService();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_u = email.getText().toString();
                String password_u = password.getText().toString();
                if(validateLogin(email_u,password_u)){
                    doLogin(email_u,password_u);
                }
            }
        });

    }

    private boolean validateLogin(String email_u, String password_u){
        if(email_u == null || email_u.trim().length() == 0){
            Toast.makeText(this,"El correo electronico es requerido",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password_u == null || password_u.trim().length() == 0){
            Toast.makeText(this,"La contraseña es requerida",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void doLogin(String email_u, String password_u){
        Call<ResponseObj> call = userService.onLogIn(email_u,password_u);
        call.enqueue(new Callback<ResponseObj>() {
            @Override
            public void onResponse(Call<ResponseObj> call, Response<ResponseObj> response) {
                if(response.isSuccessful()){
                    ResponseObj responseObj = response.body();
                    if(responseObj.getSuccessful() == true){
                        Intent intent = new Intent(LoginActivity.this,MenuBottomActivity.class);
                        //intent.putExtra("token", token + "");
                        startActivity(intent);

                    }
                    else Toast.makeText(LoginActivity.this,"La contraseña o el correo electronico es incorrecto",Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(LoginActivity.this,"A ocurrido un error, intentelo de nuevo",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseObj> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
