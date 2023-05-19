package com.envivo.futbolenvivoyendirectopartidos.rest.model.fixturedetail


import com.google.gson.annotations.SerializedName

data class Score(
    @SerializedName("extratime")
    var extratime: Any?,
    @SerializedName("fulltime")
    var fulltime: String?,
    @SerializedName("halftime")
    var halftime: String?,
    @SerializedName("penalty")
    var penalty: Any?
)