package com.info.footballlive.rest.model

data class Fixture(
    var fixture: _fixture?,
    var league: _league?,
    var teams: _teams?,
    var goals: _goals?,
    var score: _score?
)
{

    data class _fixture(
        var id: Int?,
        var referee: String?,
        var timezone: String?,
        var date: String?,
        var timestamp: Long?,
        var periods: _period?,
        var venue: _venue?,
        var status: _status?
    )

    data class _period(
        var first: Long?,
        var second: Long?
    )

    data class _venue(
        var name: String?,
        var city: String?,
        var id: Int?
    )

    data class _status(
        var long: String?,
        var short: String?,
        var elapsed: Double?
    )

    data class _league(
        var id: Int?,
        var name: String?,
        var country: String?,
        var long: String?,
        var flag: String?,
        var season: Int?,
        var round: String
    )

    data class _teams(
        var home: _team?,
        var away: _team?
    )

    data class _team(
        var id: Int?,
        var name: String?,
        var logo: String?,
        var winner: Boolean?
    )

    data class _goals(
        var home: Int?,
        var away: Int?
    )

    data class _score(
        var halftime: _goals?,
        var fulltime: _goals?,
        var extratime: _goals?,
        var penalty: _goals?
    )
}