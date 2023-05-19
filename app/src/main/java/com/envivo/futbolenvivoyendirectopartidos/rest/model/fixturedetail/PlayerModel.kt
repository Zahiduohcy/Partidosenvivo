package com.envivo.futbolenvivoyendirectopartidos.rest.model.fixturedetail

import com.envivo.futbolenvivoyendirectopartidos.rest.model.Player
import com.envivo.futbolenvivoyendirectopartidos.rest.model.Team


data class PlayerModel(
    var response: ArrayList<Response>?
){
    data class Response(
        var team: Team?,
        var players: ArrayList<Player>?
    )
}

