package com.info.footballlive.rest.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
    var code: String?,
    var country: String?,
    var founded: Int?,
    var logo: String?,
    var name: String?,
    var team_id: Int?,
    var id: Int?,
    var venue_address: String?,
    var venue_capacity: Int?,
    var venue_city: String?,
    var venue_name: String?,
    var venue_surface: String?
) : Parcelable