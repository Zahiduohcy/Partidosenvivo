package com.envivo.futbolenvivoyendirectopartidos.rest.model.fixturedetail

import com.google.gson.annotations.SerializedName


data class FixtureDetail(
    var response: List<Fixture>
){

    data class Fixture(
        @SerializedName("fixture"    ) var fixture    : mFixture?              = mFixture(),
        @SerializedName("league"     ) var league     : League?               = League(),
        @SerializedName("teams"      ) var teams      : Teams?                = Teams(),
        @SerializedName("goals"      ) var goals      : Goals?                = Goals(),
        @SerializedName("events"     ) var events     : ArrayList<Events>     = arrayListOf(),
        @SerializedName("lineups"    ) var lineups    : ArrayList<Lineups>    = arrayListOf(),
        @SerializedName("statistics" ) var statistics : ArrayList<Statistics> = arrayListOf()

    )
    data class Periods (

        @SerializedName("first"  ) var first  : Int? = null,
        @SerializedName("second" ) var second : Int? = null

    )

    data class Venue (

        @SerializedName("id"   ) var id   : Int?    = null,
        @SerializedName("name" ) var name : String? = null,
        @SerializedName("city" ) var city : String? = null

    )

    data class Status (

        @SerializedName("long"    ) var long    : String? = null,
        @SerializedName("short"   ) var short   : String? = null,
        @SerializedName("elapsed" ) var elapsed : Int?    = null

    )

    data class mFixture (

        @SerializedName("id"        ) var id        : Int?     = null,
        @SerializedName("referee"   ) var referee   : String?  = null,
        @SerializedName("timezone"  ) var timezone  : String?  = null,
        @SerializedName("date"      ) var date      : String?  = null,
        @SerializedName("timestamp" ) var timestamp : Int?     = null,
        @SerializedName("periods"   ) var periods   : Periods? = Periods(),
        @SerializedName("venue"     ) var venue     : Venue?   = Venue(),
        @SerializedName("status"    ) var status    : Status?  = Status()

    )

    data class League (

        @SerializedName("id"      ) var id      : Int?    = null,
        @SerializedName("name"    ) var name    : String? = null,
        @SerializedName("country" ) var country : String? = null,
        @SerializedName("logo"    ) var logo    : String? = null,
        @SerializedName("flag"    ) var flag    : String? = null,
        @SerializedName("season"  ) var season  : Int?    = null,
        @SerializedName("round"   ) var round   : String? = null

    )

    data class Home (

        @SerializedName("id"     ) var id     : Int?     = null,
        @SerializedName("name"   ) var name   : String?  = null,
        @SerializedName("logo"   ) var logo   : String?  = null,
        @SerializedName("winner" ) var winner : Boolean? = null

    )

    data class Away (

        @SerializedName("id"     ) var id     : Int?     = null,
        @SerializedName("name"   ) var name   : String?  = null,
        @SerializedName("logo"   ) var logo   : String?  = null,
        @SerializedName("winner" ) var winner : Boolean? = null

    )

    data class Teams (

        @SerializedName("home" ) var home : Home? = Home(),
        @SerializedName("away" ) var away : Away? = Away()

    )

    data class Goals (

        @SerializedName("home" ) var home : Int? = null,
        @SerializedName("away" ) var away : Int? = null

    )

    data class Time (

        @SerializedName("elapsed" ) var elapsed : Int?    = null,
        @SerializedName("extra"   ) var extra   : String? = null

    )

    data class Team (

        @SerializedName("id"   ) var id   : Int?    = null,
        @SerializedName("name" ) var name : String? = null,
        @SerializedName("logo" ) var logo : String? = null,
        @SerializedName("colors" ) var colors : Colors? = Colors()


    )

    data class Player (

        @SerializedName("id"   ) var id   : Int?    = null,
        @SerializedName("name" ) var name : String? = null,
        @SerializedName("primary" ) var primary : String? = null,
        @SerializedName("number"  ) var number  : String? = null,
        @SerializedName("border"  ) var border  : String? = null,
        @SerializedName("pos"    ) var pos    : String? = null,
        @SerializedName("grid"   ) var grid   : String? = null
    )

    data class Assist (

        @SerializedName("id"   ) var id   : Int?    = null,
        @SerializedName("name" ) var name : String? = null

    )

    data class Events (

        @SerializedName("time"     ) var time     : Time?   = Time(),
        @SerializedName("team"     ) var team     : Team?   = Team(),
        @SerializedName("player"   ) var player   : Player? = Player(),
        @SerializedName("assist"   ) var assist   : Assist? = Assist(),
        @SerializedName("type"     ) var type     : String? = null,
        @SerializedName("detail"   ) var detail   : String? = null,
        @SerializedName("comments" ) var comments : String? = null

    )

    data class Goalkeeper (

        @SerializedName("primary" ) var primary : String? = null,
        @SerializedName("number"  ) var number  : String? = null,
        @SerializedName("border"  ) var border  : String? = null

    )

    data class Colors (

        @SerializedName("player"     ) var player     : Player?     = Player(),
        @SerializedName("goalkeeper" ) var goalkeeper : Goalkeeper? = Goalkeeper()

    )

    data class StartXI (

        @SerializedName("player" ) var player : Player? = Player()

    )

    data class Substitutes (

        @SerializedName("player" ) var player : Player? = Player()

    )

    data class Coach (

        @SerializedName("id"    ) var id    : Int?    = null,
        @SerializedName("name"  ) var name  : String? = null,
        @SerializedName("photo" ) var photo : String? = null

    )

    data class Lineups (

        @SerializedName("team"        ) var team        : Team?                  = Team(),
        @SerializedName("formation"   ) var formation   : String?                = null,
        @SerializedName("startXI"     ) var startXI     : ArrayList<StartXI>     = arrayListOf(),
        @SerializedName("substitutes" ) var substitutes : ArrayList<Substitutes> = arrayListOf(),
        @SerializedName("coach"       ) var coach       : Coach?                 = Coach()

    )

    data class Statistics (

        @SerializedName("type"  ) var type  : String? = null,
        @SerializedName("value" ) var value : Int?    = null

    )
}

