package com.ws.covid_19.ui.main

import com.ws.covid_19.models.Global.GlobalModelItem
import com.ws.covid_19.models.Provinsi.ProvinsiModelItem

interface IMainView {
    fun onStartView()
    fun onGlobalDataCompleteFromApi(global: GlobalModelItem)
    fun onProvinsiDataCompleteFromApi(provinsi: ProvinsiModelItem)
    fun onDataErrorFromApi(throwable: Throwable)

}