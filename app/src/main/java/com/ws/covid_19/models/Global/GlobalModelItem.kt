package com.ws.covid_19.models.Global


import com.google.gson.annotations.SerializedName

data class GlobalModelItem(
    @SerializedName("attributes")
    val attributes: Attributes
)