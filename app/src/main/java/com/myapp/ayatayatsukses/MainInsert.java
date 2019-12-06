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

public class MainInsert extends AppCompatActivity {
    EditText et_nama, et_isi, et_arti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_form);

        et_nama = (EditText) findViewById(R.id.edit_nama);
        et_isi = (EditText) findViewById(R.id.edit_isi);
        et_arti = (EditText) findViewById(R.id.edit_arti);

        Button btnInsert = (Button) findViewById(R.id.tambah);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sNama = String.valueOf(et_nama.getText());
                String sIsi = String.valueOf(et_isi.getText());
                String sArti = String.valueOf(et_arti.getText());

                if (sNama.equals("")){
                    Toast.makeText(MainInsert.this, "Silahkan isi Nama Ayat", Toast.LENGTH_SHORT).show();
                } else if (sIsi.equals("")){
                    Toast.makeText(MainInsert.this, "Silahkan isi Isi Ayat", Toast.LENGTH_SHORT).show();
                }else if (sArti.equals("")){
                    Toast.makeText(MainInsert.this, "Silahkan isi Arti Ayat", Toast.LENGTH_SHORT).show();
                } else {
                    insertData(sNama,sIsi,sArti);
                }
            }
        });

    }

    public void insertData(String nama, String isi, String arti) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.ROOT_URL)
                .addConverterFactory(new StringConverter())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<ResponseBody> call = service.tambahData(nama, isi, arti);
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

                Toast.makeText(MainInsert.this, respon, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    public void lihat_insert(View view) {
        Intent menu = new Intent(MainInsert.this,DaftarAyat.class);
        startActivity(menu);
    }

}
