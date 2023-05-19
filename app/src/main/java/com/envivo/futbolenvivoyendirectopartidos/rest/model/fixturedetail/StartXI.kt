package com.envivo.futbolenvivoyendirectopartidos.rest.model.fixturedetail


import com.google.gson.annotations.SerializedName

data class StartXI(
    @SerializedName("number")
    var number: Int?,
    @SerializedName("player")
    var player: String?,
    @SerializedName("player_id")
    var playerId: Int?,
    @SerializedName("pos")
    var pos: String?,
    @SerializedName("team_id")
    var teamId: Int?
)