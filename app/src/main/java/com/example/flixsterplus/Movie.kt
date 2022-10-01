package com.example.flixsterplus

import com.google.gson.annotations.SerializedName

class Movie {
    @JvmField
    @SerializedName("title")
    var title:String? = null

    @JvmField
    @SerializedName("overview")
    var overview:String? = null

    @JvmField
    @SerializedName("poster_path")
    var posterUrl:String? = null
}