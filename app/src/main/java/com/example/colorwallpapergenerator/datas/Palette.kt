package com.example.colorwallpapergenerator.datas

import com.google.gson.annotations.SerializedName

data class Palette(
    @SerializedName("id")
    val id: Int,
    @SerializedName("palette")
    val palette: List<String>
)