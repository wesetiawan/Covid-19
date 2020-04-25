package com.ws.covid_19.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ws.covid_19.R
import com.ws.covid_19.models.Global.GlobalModelItem
import com.ws.covid_19.models.Provinsi.ProvinsiModel
import com.ws.covid_19.models.Provinsi.ProvinsiModelItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),IMainView {

    private lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPresenter = MainPresenter(this)

        onStartView()

    }

    override fun onStartView() {
        mainPresenter.getIndonesiaData()
        mainPresenter.getProvinsiData("Jawa ")
    }

    override fun onGlobalDataCompleteFromApi(global: GlobalModelItem) {
        tv_lastUpdate.text = mainPresenter.timestampFormat(global.attributes.lastUpdate)
    }

    override fun onProvinsiDataCompleteFromApi(provinsi: ProvinsiModelItem) {
        val attributes = provinsi.attributes
        tv_country.text = attributes.provinsi
        tv_total_meninggal.text = attributes.kasusMeni.toString()
        tv_total_sembuh.text = attributes.kasusSemb.toString()
        tv_total_positif.text = attributes.kasusPosi.toString()
    }

    override fun onDataErrorFromApi(throwable: Throwable) {
        error("error-----------------------------> ${throwable.localizedMessage}")
    }


}