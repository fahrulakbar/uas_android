package com.myapp.ayatayatsukses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.myapp.ayatayatsukses.Model.Biodata;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "Biodata";

    private static final String TABLE_BIODATA = "t_biodata";

    private static final String KEY_ID = "id";
    private static final String KEY_NAMADOSEN = "namaDosen";
    private static final String KEY_NAMAASLAB = "namaAslab";
    private static final String KEY_NAMAKELOMPOK = "namaKelompok";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BUKU_TABLE = "CREATE TABLE " + TABLE_BIODATA + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAMADOSEN + " TEXT,"
                + KEY_NAMAASLAB + " TEXT," + KEY_NAMAKELOMPOK + " TEXT" + ")";
        db.execSQL(CREATE_BUKU_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BIODATA);

        onCreate(db);
    }

    public void save(Biodata bio){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAMADOSEN, bio.getNamaDosen());
        values.put(KEY_NAMAASLAB, bio.getNamaAslab());
        values.put(KEY_NAMAKELOMPOK, bio.getNamaKelompok());

        db.insert(TABLE_BIODATA, null, values);
        db.close();
    }

    public List<Biodata> findAll(){
        List<Biodata> listBio=new ArrayList<Biodata>();
        String query="SELECT * FROM "+TABLE_BIODATA;

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Biodata biodata=new Biodata();
                biodata.setId(Integer.valueOf(cursor.getString(0)));
                biodata.setNamaDosen(cursor.getString(1));
                biodata.setNamaAslab(cursor.getString(2));
                biodata.setNamaKelompok(cursor.getString(3));
                listBio.add(biodata);
            }while(cursor.moveToNext());
        }

        return listBio;
    }
}
