package com.example.flixsterplus

import com.google.gson.annotations.SerializedName

class Television {
    @JvmField
    @SerializedName("name")
    var name:String? = null

    @JvmField
    @SerializedName("popularity")
    var popularity:Float? = null

    @JvmField
    @SerializedName("poster_path")
    var posterUrl:String? = null

    @JvmField
    @SerializedName("overview")
    var overview:String? = null
}