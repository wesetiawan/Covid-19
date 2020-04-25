package com.ws.covid_19.api

import com.ws.covid_19.models.Global.GlobalModel
import com.ws.covid_19.models.Provinsi.ProvinsiModel
import com.ws.covid_19.util.Constant
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitServices{

    @GET(" ")
    fun getGlobal() : Call<GlobalModel>
    @GET("indonesia/provinsi")
    fun getProvinsi(): Call<ProvinsiModel>

    companion object{
        fun create(): RetrofitServices{
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build()
            return retrofit.create(RetrofitServices::class.java)
        }
    }
}