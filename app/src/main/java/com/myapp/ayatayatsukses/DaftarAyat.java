package com.myapp.ayatayatsukses;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.myapp.ayatayatsukses.API.ApiService;
import com.myapp.ayatayatsukses.Adapter.ListArrayAdapter;
import com.myapp.ayatayatsukses.MainActivity;
import com.myapp.ayatayatsukses.Model.ModelData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DaftarAyat extends AppCompatActivity implements AdapterView.OnItemClickListener{


    ArrayList<ModelData> dataayat = new ArrayList<ModelData>();
    ListView listview;
    ListArrayAdapter adapter;

    LinearLayout layout_loading;
    TextView text_load;
    ImageView icon_load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftar_ayat);

        layout_loading = (LinearLayout) findViewById(R.id.layout_loading);

        text_load = (TextView) findViewById(R.id.text_load);
        icon_load = (ImageView) findViewById(R.id.icon_load);

        listview = (ListView) findViewById(R.id.listAyatAyat);
        listview.setOnItemClickListener(DaftarAyat.this);
        listview.setDividerHeight(0);
        setup();

    }

    public void setup() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<List<ModelData>> call = service.getSemuaAyat();
        call.enqueue(new Callback<List<ModelData>>() {
            @Override
            public void onResponse(Call<List<ModelData>> call, Response<List<ModelData>> response) {

                if (response.isSuccessful()) {
                    int jumlah = response.body().size();

                    for (int i = 0; i < jumlah; i++) {

                        ModelData data = new ModelData(
                                response.body().get(i).getId(),
                                response.body().get(i).getNm_ayat(),
                                response.body().get(i).getIsi_ayat(),
                                response.body().get(i).getArti_ayat());

                        System.out.println(response.body().get(i).getId());
                        System.out.println( response.body().get(i).getNm_ayat());
                        dataayat.add(data);
                        Log.d("RESPON", "onResponse: " + response.body().get(i).getId());

                    }
                    listview.setVisibility(View.VISIBLE);
                    adapter = new ListArrayAdapter(DaftarAyat.this, R.layout.row_ayat, dataayat);
                    listview.setAdapter(adapter);

                    if (adapter.getCount() < 1 ) {
                        layout_loading.setVisibility(View.VISIBLE);
                        String error = "List Ayat Kosong";
                        text_load.setText(error);
                        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_data_kosong);
                        icon_load.setImageBitmap(icon);
                    } else {
                        layout_loading.setVisibility(View.GONE);
                    }
                } else {
                    String error = "Error Retrive Data from Server !!!";
                    text_load.setText(error);
                    Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_network);
                    icon_load.setImageBitmap(icon);

                }

            }

            @Override
            public void onFailure(Call<List<ModelData>> call, Throwable t) {
                String error = "Error Retrive Data from Server !!!\n" + t.getMessage();
                text_load.setText(error);
                Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_network);
                icon_load.setImageBitmap(icon);
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView ids = (TextView) view.findViewById(R.id.tvID);
        Intent intent = new Intent(DaftarAyat.this, MainEdit.class);
        intent.putExtra(ModelData.idAyat, ids.getText().toString());
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            adapter.clear();
            setup();
        }
    }

    public void tambahData(View view) {
        Intent tambah = new Intent(DaftarAyat.this,MainInsert.class);
        startActivity(tambah);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenulogout , menu);
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.logout){
            startActivity(new Intent(this, DaftarAyatUser.class));
        }
        return true;
    }
}
