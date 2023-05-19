package com.envivo.futbolenvivoyendirectopartidos.rest.model

import com.google.gson.annotations.SerializedName

data class Standing(
    var league: League?
) {


    data class League(
        var id: Int?,
        var name: String?,
        var country: String?,
        var logo: String?,
        var flag: String?,
        var season: Int?,
        var standings: List<_Standing>?
    )

    data class _Standing(
        var rank: Int?,
        var team: Team,
        var points: Int?,
        var goalsDiff: Int?,
        var group: String?,
        var form: String?,
        var status: String?,
        var description: String?,
        var update: String?,
        var all: Stat?,
        var home: Stat?,
        var away: Stat?,
        var league: _League?

    )

    data class _League(
        var id: Int?,
        var name: String?,
        var country: Country?,
        var logo: String?,
        var flag: String?,
        var season: String?
    )

    data class Team(
        var id: Int?,
        var name: String?,
        var logo: String?
    )

    data class Stat(
        var played: Int?,
        var win: Int?,
        var draw: Int?,
        var lose: Int,
        var goals: Goals
    )

    data class Goals(
        @SerializedName("for")
        var _for: Int?,
        var against: Int??

    )
}