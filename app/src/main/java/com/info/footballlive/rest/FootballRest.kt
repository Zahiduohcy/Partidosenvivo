package com.info.footballlive.rest

import com.info.footballlive.rest.model.*
import io.reactivex.Flowable
import retrofit2.http.GET

import com.info.footballlive.rest.model.fixturedetail.FixtureDetail
import com.info.footballlive.rest.model.fixturedetail.FixtureModel
import com.info.footballlive.rest.model.fixturedetail.LeagueModel
import com.info.footballlive.rest.model.fixturedetail.PlayerModel
import retrofit2.http.Path
import retrofit2.http.Query

interface FootballRest {

    // common
    @GET("/v3/countries")
    fun getCountries() : Flowable<CountryModel>

    @GET("/v3/seasons")
    fun getSeasons() : Flowable<List<Int>>

    // leagues
    @GET("/v3/leagues")
    fun getLeagues(@Query("code") countryCode: String) : Flowable<LeagueModel>

    @GET("/v3/leagues")
    fun getGlobalLeagues() : Flowable<LeagueModel>

    // standings
    @GET("/v3/standings")
    fun getStandings(@Query("league") league_id: Int, @Query("season") season: Int) : Flowable<StandingModel>

    // fixtures
    @GET("/v3/fixtures")
    fun getFixtures(@Query("league") league_id: Int, @Query("season") season: Int) : Flowable<FixtureModel>

    @GET("/v3/fixtures")
    fun getFixtureDetail(@Query("id") fixture_id: Int) : Flowable<FixtureDetail>

    // teams
    @GET("teams/league/{league_id}")
    fun getTeams(@Path("league_id") league_id: Int) : Flowable<List<Team>>

    @GET("statistics/{league_id}/{team_id}")
    fun getTeamStatistics(@Path("league_id") league_id: Int, @Path("team_id") team_id: Int) : Flowable<TeamStatistics>

    // players
    @GET("/v3/players/squads")
    fun getPlayers(@Query("team") team_id: Int) : Flowable<PlayerModel>

    @GET("players/player/{player_id}")
    fun getPlayerDetail(@Path("player_id") player_id: Int) : Flowable<List<PlayerDetail>>
}