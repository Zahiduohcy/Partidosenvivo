package com.envivo.futbolenvivoyendirectopartidos.rest.model

import com.google.gson.annotations.SerializedName

data class StandingModel (

    @SerializedName("get"        ) var get        : String?             = null,
    @SerializedName("parameters" ) var parameters : Parameters?         = Parameters(),
    @SerializedName("errors"     ) var errors     : ArrayList<String>   = arrayListOf(),
    @SerializedName("results"    ) var results    : Int?                = null,
    @SerializedName("paging"     ) var paging     : Paging?             = Paging(),
    @SerializedName("response"   ) var response   : ArrayList<Response> = arrayListOf()

){
    data class Parameters (

        @SerializedName("league" ) var league : String? = null,
        @SerializedName("season" ) var season : String? = null

    )

    data class Paging (

        @SerializedName("current" ) var current : Int? = null,
        @SerializedName("total"   ) var total   : Int? = null

    )

    data class Team (

        @SerializedName("id"   ) var id   : Int?    = null,
        @SerializedName("name" ) var name : String? = null,
        @SerializedName("logo" ) var logo : String? = null

    )

    data class All (

        @SerializedName("played" ) var played : Int?   = null,
        @SerializedName("win"    ) var win    : Int?   = null,
        @SerializedName("draw"   ) var draw   : Int?   = null,
        @SerializedName("lose"   ) var lose   : Int?   = null,
        @SerializedName("goals"  ) var goals  : Goals? = Goals()

    )

    data class Home (

        @SerializedName("played" ) var played : Int?   = null,
        @SerializedName("win"    ) var win    : Int?   = null,
        @SerializedName("draw"   ) var draw   : Int?   = null,
        @SerializedName("lose"   ) var lose   : Int?   = null,
        @SerializedName("goals"  ) var goals  : Goals? = Goals()

    )

    data class Goals (

        @SerializedName("for"     ) var _for     : Int? = null,
        @SerializedName("against" ) var against : Int? = null

    )

    data class Away (

        @SerializedName("played" ) var played : Int?   = null,
        @SerializedName("win"    ) var win    : Int?   = null,
        @SerializedName("draw"   ) var draw   : Int?   = null,
        @SerializedName("lose"   ) var lose   : Int?   = null,
        @SerializedName("goals"  ) var goals  : Goals? = Goals()

    )


    data class Standings (

        @SerializedName("rank"        ) var rank        : Int?    = null,
        @SerializedName("team"        ) var team        : Team?   = Team(),
        @SerializedName("points"      ) var points      : Int?    = null,
        @SerializedName("goalsDiff"   ) var goalsDiff   : Int?    = null,
        @SerializedName("group"       ) var group       : String? = null,
        @SerializedName("form"        ) var form        : String? = null,
        @SerializedName("status"      ) var status      : String? = null,
        @SerializedName("description" ) var description : String? = null,
        @SerializedName("all"         ) var all         : All?    = All(),
        @SerializedName("home"        ) var home        : Home?   = Home(),
        @SerializedName("away"        ) var away        : Away?   = Away(),
        @SerializedName("update"      ) var update      : String? = null,
        var league: League? = null

    )

    data class League (

        @SerializedName("id"        ) var id        : Int?                            = null,
        @SerializedName("name"      ) var name      : String?                         = null,
        @SerializedName("country"   ) var country   : String?                         = null,
        @SerializedName("logo"      ) var logo      : String?                         = null,
        @SerializedName("flag"      ) var flag      : String?                         = null,
        @SerializedName("season"    ) var season    : Int?                            = null,
        @SerializedName("standings" ) var standings : ArrayList<ArrayList<Standings>> = arrayListOf()

    )

    data class Response (

        @SerializedName("league" ) var league : League? = League()

    )
}
