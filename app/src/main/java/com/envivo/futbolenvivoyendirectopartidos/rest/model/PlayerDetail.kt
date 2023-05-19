package com.envivo.futbolenvivoyendirectopartidos.rest.model

data class PlayerDetail(
    var age: Int?,
    var birth_country: String?,
    var birth_date: String?,
    var birth_place: String?,
    var captain: Int?,
    var cards: Cards?,
    var dribbles: Dribbles?,
    var duels: Duels?,
    var firstname: String?,
    var fouls: Fouls?,
    var games: Games?,
    var goals: Goals?,
    var height: String?,
    var injured: Any?,
    var lastname: String?,
    var league: String?,
    var nationality: String?,
    var number: Any?,
    var passes: Passes?,
    var penalty: Penalty?,
    var player_id: Int?,
    var player_name: String?,
    var position: String?,
    var rating: Any?,
    var season: String?,
    var shots: Shots?,
    var substitutes: Substitutes?,
    var tackles: Tackles?,
    var team_id: Int?,
    var team_name: String?,
    var weight: String?
) {
    data class Goals(
        var assists: Int?,
        var conceded: Int?,
        var total: Int?
    )

    data class Penalty(
        var missed: Int?,
        var saved: Int?,
        var success: Int?
    )

    data class Substitutes(
        var `in`: Int?,
        var `out`: Int?,
        var bench: Int?
    )

    data class Shots(
        var on: Int?,
        var total: Int?
    )

    data class Games(
        var appearences: Int?,
        var lineups: Int?,
        var minutes_played: Int?
    )

    data class Tackles(
        var blocks: Int?,
        var interceptions: Int?,
        var total: Int?
    )

    data class Fouls(
        var committed: Int?,
        var drawn: Int?
    )

    data class Passes(
        var accuracy: Int?,
        var total: Int?
    )

    data class Duels(
        var total: Int?,
        var won: Int?
    )

    data class Cards(
        var red: Int?,
        var yellow: Int?,
        var yellowred: Int?
    )

    data class Dribbles(
        var attempts: Int?,
        var success: Int?
    )
}