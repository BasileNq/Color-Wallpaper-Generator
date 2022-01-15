package com.example.colorwallpapergenerator.datas

import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class Wallpaper(
    @SerializedName("type")
    val type: String,
    @SerializedName("data")
    val data: List<Palette>
)