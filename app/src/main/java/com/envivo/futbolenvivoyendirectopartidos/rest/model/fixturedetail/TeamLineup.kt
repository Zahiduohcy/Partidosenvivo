package com.envivo.futbolenvivoyendirectopartidos.rest.model.fixturedetail


import com.google.gson.annotations.SerializedName

data class TeamLineup(
    @SerializedName("formation")
    var formation: String?,
    @SerializedName("startXI")
    var startXI: List<StartXI?>?,
    @SerializedName("substitutes")
    var substitutes: List<Substitute?>?
)