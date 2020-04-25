package com.ws.covid_19.ui.main

import android.content.Context
import com.ws.covid_19.api.RetrofitServices
import com.ws.covid_19.models.Global.GlobalModel
import com.ws.covid_19.models.Global.GlobalModelItem
import com.ws.covid_19.models.Provinsi.ProvinsiModel
import com.ws.covid_19.models.Provinsi.ProvinsiModelItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class MainPresenter(context: Context) {
    val mainView = context as IMainView
    var retrofitServices = RetrofitServices.create()

    fun getIndonesiaData() {
        retrofitServices.getGlobal()
            .enqueue(object : Callback<GlobalModel>{
                override fun onFailure(call: Call<GlobalModel>, t: Throwable) {
                    mainView.onDataErrorFromApi(t)
                }

                override fun onResponse(call: Call<GlobalModel>, response: Response<GlobalModel>) {
                    mainView.onGlobalDataCompleteFromApi(response.body()?.
                    find { it.attributes.countryRegion.contains("indonesia", true) } as GlobalModelItem)
                }
            })

    }

    fun getProvinsiData(provinsiName: String){
        retrofitServices.getProvinsi()
            .enqueue(object : Callback<ProvinsiModel>{
                override fun onFailure(call: Call<ProvinsiModel>, t: Throwable) {
                    mainView.onDataErrorFromApi(t)
                }

                override fun onResponse(
                    call: Call<ProvinsiModel>, response: Response<ProvinsiModel>) {
                    mainView.onProvinsiDataCompleteFromApi(response.body()?.
                    find { it.attributes.provinsi.contains(provinsiName, true) } as ProvinsiModelItem)
                }

            })
    }

    fun timestampFormat(time: Long): String {
        val sdf = SimpleDateFormat("d MMMM yyyy HH:mm:ss", Locale.ROOT)
        val netDate = Date(time)
        return sdf.format(netDate)
    }


}

