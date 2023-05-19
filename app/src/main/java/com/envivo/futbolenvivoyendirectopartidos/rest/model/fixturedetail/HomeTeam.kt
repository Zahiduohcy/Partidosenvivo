package com.envivo.futbolenvivoyendirectopartidos.rest.model.fixturedetail


import com.google.gson.annotations.SerializedName

data class HomeTeam(
    @SerializedName("logo")
    var logo: String?,
    @SerializedName("team_id")
    var teamId: Int?,
    @SerializedName("team_name")
    var teamName: String?
)