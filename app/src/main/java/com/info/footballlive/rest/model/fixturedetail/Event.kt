package com.info.footballlive.rest.model.fixturedetail


import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("detail")
    var detail: String?,
    @SerializedName("elapsed")
    var elapsed: Int?,
    @SerializedName("player")
    var player: String?,
    @SerializedName("player_id")
    var playerId: Int?,
    @SerializedName("team_id")
    var teamId: Int?,
    @SerializedName("teamName")
    var teamName: String?,
    @SerializedName("type")
    var type: String?
)