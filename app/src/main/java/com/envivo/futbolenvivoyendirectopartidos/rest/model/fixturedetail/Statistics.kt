package com.envivo.futbolenvivoyendirectopartidos.rest.model.fixturedetail


import com.google.gson.annotations.SerializedName

data class Statistics(
    @SerializedName("Ball Possession")
    var ballPossession: BallPossession?,
    @SerializedName("Blocked Shots")
    var blockedShots: BlockedShots?,
    @SerializedName("Corner Kicks")
    var cornerKicks: CornerKicks?,
    @SerializedName("Fouls")
    var fouls: Fouls?,
    @SerializedName("Goalkeeper Saves")
    var goalkeeperSaves: GoalkeeperSaves?,
    @SerializedName("Offsides")
    var offsides: Offsides?,
    @SerializedName("Passes %")
    var passes: Passes?,
    @SerializedName("Passes accurate")
    var passesAccurate: PassesAccurate?,
    @SerializedName("Red Cards")
    var redCards: RedCards?,
    @SerializedName("Shots insidebox")
    var shotsInsidebox: ShotsInsidebox?,
    @SerializedName("Shots off Goal")
    var shotsOffGoal: ShotsOffGoal?,
    @SerializedName("Shots on Goal")
    var shotsOnGoal: ShotsOnGoal?,
    @SerializedName("Shots outsidebox")
    var shotsOutsidebox: ShotsOutsidebox?,
    @SerializedName("Total passes")
    var totalPasses: TotalPasses?,
    @SerializedName("Total Shots")
    var totalShots: TotalShots?,
    @SerializedName("Yellow Cards")
    var yellowCards: YellowCards?
)