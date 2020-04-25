package com.ws.covid_19.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ws.covid_19.R
import com.ws.covid_19.models.Global.GlobalModelItem
import com.ws.covid_19.models.Provinsi.ProvinsiModelItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),IMainView {

    private lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPresenter = MainPresenter(this)

        mainPresenter.getIndonesiaData()
        mainPresenter.getProvinsiData("Sumatera ")

    }

    override fun onDataProgress() {
        TODO("Not yet implemented")
    }

    override fun onGlobalDataCompleteFromApi(global: GlobalModelItem) {
        //tv_country.text = data.attributes.countryRegion
        //tv_total_meninggal.text = data.attributes.deaths.toString()
        //tv_total_sembuh.text = data.attributes.recovered.toString()
        //tv_total_positif.text = data.attributes.confirmed.toString()
        tv_lastUpdate.text = mainPresenter.timestampFormat(global.attributes.lastUpdate)
    }

    override fun onProvinsiDataCompleteFromApi(provinsi: ProvinsiModelItem) {
        tv_country.text = provinsi.attributes.provinsi
        tv_total_meninggal.text = provinsi.attributes.kasusMeni.toString()
        tv_total_sembuh.text = provinsi.attributes.kasusSemb.toString()
        tv_total_positif.text = provinsi.attributes.kasusPosi.toString()
    }

    override fun onDataErrorFromApi(throwable: Throwable) {
        error("error-----------------------------> ${throwable.localizedMessage}")
    }


}