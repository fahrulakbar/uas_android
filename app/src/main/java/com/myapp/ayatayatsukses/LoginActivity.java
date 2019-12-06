package com.myapp.ayatayatsukses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.myapp.ayatayatsukses.API.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        username = (EditText) findViewById(R.id.et_username);
        password = (EditText) findViewById(R.id.et_password);

        login = (Button) findViewById(R.id.btn_login);

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String user_name = username.getText().toString();
                String pass_word = password.getText().toString();

                Retrofit retrofit = new Retrofit.Builder().baseUrl(MainActivity.ROOT_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiService request = retrofit.create(ApiService.class);
                Call<JsonResponse> call = request.login(user_name,pass_word);
                call.enqueue(new Callback<JsonResponse>() {

                    @Override
                    public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {
                        if (response.code()==200) {
                            JsonResponse jsonResponse = response.body();
                            Toast.makeText(getApplicationContext(),jsonResponse.getResponse().toString(),Toast.LENGTH_SHORT).show();
                            if (jsonResponse.getResponse().toString().isEmpty()) {
                                Toast.makeText(LoginActivity.this, "Username atau Password salah ! ", Toast.LENGTH_SHORT).show();
                            }else {
                                Intent login_activity = new Intent(LoginActivity.this,DaftarAyat.class);
                            startActivity(login_activity);
                            }
                        }else {
                            Toast.makeText(getApplicationContext(),String.valueOf(response.code()),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });
    }
}
