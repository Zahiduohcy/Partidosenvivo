package com.info.footballlive.rest.model.fixturedetail

import com.info.footballlive.rest.model.Player
import com.info.footballlive.rest.model.Team


data class PlayerModel(
    var response: ArrayList<Response>?
){
    data class Response(
        var team: Team?,
        var players: ArrayList<Player>?
    )
}

