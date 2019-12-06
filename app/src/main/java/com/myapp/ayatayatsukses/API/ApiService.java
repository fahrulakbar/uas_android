package com.myapp.ayatayatsukses.API;

import com.myapp.ayatayatsukses.JsonResponse;
import com.myapp.ayatayatsukses.Model.ModelData;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
      @FormUrlEncoded
      @POST("insert.php")
      Call<ResponseBody> tambahData(@Field("nama") String nama, @Field("ayat") String isi, @Field("keterangan") String arti);
//
    @FormUrlEncoded
    @POST("update.php")
    Call<ResponseBody> editData(@Field("id") int id, @Field("nama") String nama, @Field("ayat") String isi, @Field("keterangan") String arti);
//
    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponseBody> hapusData(@Field("id") int id);
    @GET("read.php")
    Call<List<ModelData>> getSemuaAyat();

    @GET("single.php")
    Call<List<ModelData>> getSingleData(@Query("id") int id);

    @FormUrlEncoded
    @POST("login.php")
    Call<JsonResponse> login(@Field("username") String username, @Field("password") String password);
    
}
