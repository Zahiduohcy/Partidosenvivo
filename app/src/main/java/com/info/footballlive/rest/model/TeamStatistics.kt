package com.info.footballlive.rest.model

data class TeamStatistics(
    var goals: Goals?,
    var goalsAvg: GoalsAvg?,
    var matchs: Matchs?
) {
    data class Goals(
        var goalsAgainst: GoalsAgainst?,
        var goalsFor: GoalsFor?
    ) {
        data class GoalsFor(
            var away: Int?,
            var home: Int?,
            var total: Int?
        )

        data class GoalsAgainst(
            var away: Int?,
            var home: Int?,
            var total: Int?
        )
    }

    data class Matchs(
        var draws: Draws?,
        var loses: Loses?,
        var matchsPlayed: MatchsPlayed?,
        var wins: Wins?
    ) {
        data class MatchsPlayed(
            var away: Int?,
            var home: Int?,
            var total: Int?
        )

        data class Wins(
            var away: Int?,
            var home: Int?,
            var total: Int?
        )

        data class Loses(
            var away: Int?,
            var home: Int?,
            var total: Int?
        )

        data class Draws(
            var away: Int?,
            var home: Int?,
            var total: Int?
        )
    }

    data class GoalsAvg(
        var goalsAgainst: GoalsAgainst?,
        var goalsFor: GoalsFor?
    ) {
        data class GoalsFor(
            var away: String?,
            var home: String?,
            var total: String?
        )

        data class GoalsAgainst(
            var away: String?,
            var home: String?,
            var total: String?
        )
    }
}