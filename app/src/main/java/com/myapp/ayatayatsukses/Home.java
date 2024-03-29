package com.myapp.ayatayatsukses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    ViewFlipper v_flipper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        int images[] = {R.drawable.bg1, R.drawable.bg2, R.drawable.bg3};

        v_flipper = findViewById(R.id.v_flipper);

        for (int image: images){
            flipperImage(image);
        }
    }

    public void flipperImage(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);
        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(this, android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }

    public void goAyatSukses1(View view) {
        Intent goAyatSukses = new Intent(Home.this,DisplayData.class);
        goAyatSukses.putExtra("data1", "1");
        startActivity(goAyatSukses);
    }
    public void goAyatSukses2(View view) {
        Intent goAyatSukses = new Intent(Home.this,DisplayData.class);
        goAyatSukses.putExtra("data1", "2");
        startActivity(goAyatSukses);
    }
    public void goAyatSukses3(View view) {
        Intent goAyatSukses = new Intent(Home.this,DisplayData.class);
        goAyatSukses.putExtra("data1", "3");
        startActivity(goAyatSukses);
    }
    public void goAyatSukses4(View view) {
        Intent goAyatSukses = new Intent(Home.this,DisplayData.class);
        goAyatSukses.putExtra("data1", "4");
        startActivity(goAyatSukses);
    }
    public void goAyatSukses5(View view) {
        Intent goAyatSukses = new Intent(Home.this,DisplayData.class);
        goAyatSukses.putExtra("data1", "5");
        startActivity(goAyatSukses);
    }
    public void goAyatSukses6(View view) {
        Intent goAyatSukses = new Intent(Home.this,DisplayData.class);
        goAyatSukses.putExtra("data1", "6");
        startActivity(goAyatSukses);
    }
    public void goAyatSukses7(View view) {
        Intent goAyatSukses = new Intent(Home.this,DisplayData.class);
        goAyatSukses.putExtra("data1", "7");
        startActivity(goAyatSukses);
    }
    public void goAyatSukses8(View view) {
        Intent goAyatSukses = new Intent(Home.this,DisplayData.class);
        goAyatSukses.putExtra("data1", "8");
        startActivity(goAyatSukses);
    }
    public void goAyatSukses9(View view) {
        Intent goAyatSukses = new Intent(Home.this,DisplayData.class);
        goAyatSukses.putExtra("data1", "9");
        startActivity(goAyatSukses);
    }
    public void goAyatSukses10(View view) {
        Intent goAyatSukses = new Intent(Home.this,DisplayData.class);
        goAyatSukses.putExtra("data1", "10");
        startActivity(goAyatSukses);
    }
    public void goAyatSukses11(View view) {
        Intent goAyatSukses = new Intent(Home.this,AboutUs.class);
        startActivity(goAyatSukses);
    }
    public void goAyatSukses12(View view) {
        Intent goAyatSukses = new Intent(Home.this,LoginActivity.class);
        startActivity(goAyatSukses);
    }



}
