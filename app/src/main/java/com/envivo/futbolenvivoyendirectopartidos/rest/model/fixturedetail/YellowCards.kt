package com.envivo.futbolenvivoyendirectopartidos.rest.model.fixturedetail


import com.google.gson.annotations.SerializedName

data class YellowCards(
    @SerializedName("away")
    var away: String?,
    @SerializedName("home")
    var home: String?
)