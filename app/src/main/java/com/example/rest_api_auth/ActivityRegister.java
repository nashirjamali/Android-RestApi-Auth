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

public class ActivityRegister extends AppCompatActivity {

    private EditText etName, etEmail, etPassword;
    private Button btnRegister, btnLogin;

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8000/api/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    ApiInterface apiInterface = retrofit.create(ApiInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),ActivityLogin.class));
                finish();
            }
        });
    }


    private void sendData(){
        String name = etName.getText().toString();
        final String email = etEmail.getText().toString();
        final String password = etPassword.getText().toString();

        Call<valueInsert> call = apiInterface.registerUser(name, email, password);

        call.enqueue(new Callback<valueInsert>() {
            @Override
            public void onResponse(Call<valueInsert> call, Response<valueInsert> response) {
                Preferences.setLoggedInStatus(getBaseContext(), true);
                finish();
            }

            @Override
            public void onFailure(Call<valueInsert> call, Throwable t) {
                Toast.makeText(ActivityRegister.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView(){
        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnRegister = findViewById(R.id.btn_register);
        btnLogin = findViewById(R.id.btn_login);
    }
}
