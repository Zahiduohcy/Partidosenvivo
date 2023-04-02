package com.info.footballlive.rest.model.fixturedetail


import com.google.gson.annotations.SerializedName

data class ShotsOutsidebox(
    @SerializedName("away")
    var away: String?,
    @SerializedName("home")
    var home: String?
)