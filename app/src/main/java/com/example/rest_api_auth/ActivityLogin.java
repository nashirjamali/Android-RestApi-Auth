package com.example.rest_api_auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rest_api_auth.Model.valueInsert;
import com.example.rest_api_auth.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityLogin extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin, btnRegister;

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8000/api/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    ApiInterface apiInterface = retrofit.create(ApiInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

        if (Preferences.getLoggedInStatus(getBaseContext())){
            startActivity(new Intent(getBaseContext(),MainActivity.class));
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),ActivityRegister.class));
                finish();
            }
        });

    }

    private void sendData(){
        final String email = etEmail.getText().toString();
        final String password = etPassword.getText().toString();

        Call<valueInsert> call = apiInterface.loginUser(email, password);

        call.enqueue(new Callback<valueInsert>() {
            @Override
            public void onResponse(Call<valueInsert> call, Response<valueInsert> response) {

                if (response.isSuccessful()){
                    Preferences.setLoggedInStatus(getBaseContext(), true);
                    startActivity(new Intent(getBaseContext(),MainActivity.class));
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "Credentials are not Valid.", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<valueInsert> call, Throwable t) {
                Toast.makeText(ActivityLogin.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView(){
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
    }
}
