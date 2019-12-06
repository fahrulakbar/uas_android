package com.myapp.ayatayatsukses;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.myapp.ayatayatsukses.API.ApiService;
import com.myapp.ayatayatsukses.Model.ModelData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainEdit extends AppCompatActivity {
    int ID_AYAT;
    EditText et_id, et_nama, et_isi, et_arti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        ID_AYAT = Integer.parseInt(getIntent().getStringExtra(ModelData.idAyat));

        et_id = (EditText) findViewById(R.id.edit_id);
        et_nama = (EditText) findViewById(R.id.edit_nama);
        et_isi = (EditText) findViewById(R.id.edit_isi);
        et_arti = (EditText) findViewById(R.id.edit_arti);

        bindData();

        Button btnUbah = (Button) findViewById(R.id.ubah);
        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sId = Integer.parseInt(String.valueOf(et_id.getText()));
                String sNama = String.valueOf(et_nama.getText());
                String sIsi = String.valueOf(et_isi.getText());
                String sArti = String.valueOf(et_arti.getText());

                if (sId == 0){
                    Toast.makeText(MainEdit.this, "Jangan Rubah ID", Toast.LENGTH_SHORT).show();
                } else if (sNama.equals("")){
                    Toast.makeText(MainEdit.this, "Silahkan isi Nama Ayat", Toast.LENGTH_SHORT).show();
                } else if (sIsi.equals("")){
                    Toast.makeText(MainEdit.this, "Silahkan isi Isi Ayat", Toast.LENGTH_SHORT).show();
                }else if (sArti.equals("")){
                    Toast.makeText(MainEdit.this, "Silahkan isi Arti Ayat", Toast.LENGTH_SHORT).show();
                } else {
                    editData(sId,sNama,sIsi,sArti);
                }
            }
        });

        Button btnDel = (Button) findViewById(R.id.hapus);
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hapusData(ID_AYAT);
            }
        });
/*
        // ditambahkan
        Button btnBatal = (Button) findViewById(R.id.batal);
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });*/
    }

    public void bindData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<List<ModelData>> call = service.getSingleData(ID_AYAT);
        call.enqueue(new Callback<List<ModelData>>() {
            @Override
            public void onResponse(Call<List<ModelData>> call, Response<List<ModelData>> response) {

                if (response.isSuccessful()) {

                    for (int i = 0; i < response.body().size(); i++) {

                        et_id.setText(response.body().get(i).getId());
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

    public void editData(int id, String nama, String isi, String arti) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.ROOT_URL)
                .addConverterFactory(new StringConverter())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<ResponseBody> call = service.editData(id, nama, isi, arti);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                BufferedReader reader = null;
                String respon = "";

                try {
                    reader = new BufferedReader(new InputStreamReader(response.body().byteStream()));
                    respon = reader.readLine();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                Toast.makeText(MainEdit.this, respon, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    public void hapusData(int id) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.ROOT_URL)
                .addConverterFactory(new StringConverter())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<ResponseBody> call = service.hapusData(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                BufferedReader reader = null;
                String respon = "";

                try {
                    reader = new BufferedReader(new InputStreamReader(response.body().byteStream()));
                    respon = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Toast.makeText(MainEdit.this, respon, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }


    public void lihat(View view) {
        Intent menu = new Intent(MainEdit.this,DaftarAyat.class);
        startActivity(menu);
    }

}
