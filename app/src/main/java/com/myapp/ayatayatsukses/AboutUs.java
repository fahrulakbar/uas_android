package com.myapp.ayatayatsukses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.myapp.ayatayatsukses.Model.Biodata;

import java.util.List;

public class AboutUs extends AppCompatActivity {

    TextView mBioInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

//        mBioInsert = (TextView) findViewById(R.id.bio_insert);
//
//        DatabaseHandler databaseHandler=new DatabaseHandler(this);
//        databaseHandler.save(new Biodata("Nama Dosen : Supriyono M.Kom","Nama Aslab :\n1. Eka Puji Rahayu L\n2. Moch. Irsyadul Anam","Kelompok 3 :\n" +
//                "1. Fajar Dewantara" +
//                "\n2. Hamdan M.A" +
//                "\n3. Cindyana Rani"));
//
//        List<Biodata> listBio = databaseHandler.findAll();
//        for(Biodata b:listBio){
//            mBioInsert.setText(b.getNamaDosen()+"\n\n"+b.getNamaAslab()+"\n\n"+b.getNamaKelompok());
//        }

    }

    public void kembaliMenu(View view) {
        Intent menu = new Intent(AboutUs.this,Home.class);
        startActivity(menu);
    }
}
