package com.myapp.ayatayatsukses;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.myapp.ayatayatsukses.API.ApiService;
import com.myapp.ayatayatsukses.Model.ModelData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DisplayData extends AppCompatActivity {
    int ID_AYAT;
    TextView et_id, et_nama, et_isi, et_arti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_user);

        ID_AYAT = Integer.parseInt(getIntent().getStringExtra("data1"));

        et_id = (TextView) findViewById(R.id.tv_id);
        et_nama = (TextView) findViewById(R.id.tv_nama);
        et_isi = (TextView) findViewById(R.id.tv_isi);
        et_arti = (TextView) findViewById(R.id.tv_arti);

        bindData();
    }

    public void bindData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        Call<List<ModelData>>call = service.getSingleData(ID_AYAT);

        call.enqueue(new Callback<List<ModelData>>() {


            @Override
            public void onResponse(Call<List<ModelData>> call, Response<List<ModelData>> response) {
                Log.i("mesage", String.valueOf(response));
                if (response.isSuccessful()) {

                    for (int i = 0; i < response.body().size(); i++) {

                        et_id.setText(response.body().get(i).getId());
                        Log.i("mesage", String.valueOf(et_id));
                        et_nama.setText(response.body().get(i).getNm_ayat());
                        et_isi.setText(response.body().get(i).getIsi_ayat());
                        et_arti.setText(response.body().get(i).getArti_ayat());
                    }

                }

            }

            @Override
            public void onFailure(Call<List<ModelData>> call, Throwable t) {

            }
        });
    }

    public void kembaliMenu(View view) {
        Intent menu = new Intent(DisplayData.this,DaftarAyatUser.class);
        startActivity(menu);
    }
}
