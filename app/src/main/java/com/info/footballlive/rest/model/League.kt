package com.info.footballlive.rest.model

data class League(
    var country: Country?,
    var league: _league?,
    var seasons: List<_season>?
)

data class _coverage(
    var standings: Boolean?,
    var players: Boolean?,
    var top_sccores: Boolean?,
    var top_assists: Boolean?,
    var top_cards: Boolean?,
    var injuries: Boolean?,
    var predictions: Boolean?,
    var odds: Boolean?
)

data class _fixtures(
    var events: Boolean?,
    var lineups: Boolean?,
    val statistics_fixtures: Boolean?,
    var statistics_players: Boolean?


)

data class _league(
    var id: Int?,
    var name: String?,
    var logo: String?,
    var type: String?
)

data class _season(
    var year: Int?,
    var start: String?,
    var end: String?,
    var current: Boolean?,
    var coverage: _coverage?
)