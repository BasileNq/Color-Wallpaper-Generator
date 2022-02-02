package com.example.colorwallpapergenerator.utils

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.colorwallpapergenerator.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import org.json.JSONTokener
import java.io.FileInputStream
import java.lang.Exception
import java.lang.System.getProperty
import java.util.*
import java.util.logging.Logger

class Requester {

    val apiKey = BuildConfig.API_KEY
    private val currentWallpaper = MutableLiveData<String>()
    private val apiURL: String = "https://random-palette-generator.p.rapidapi.com/palette/"
    var client: OkHttpClient = OkHttpClient()

    /* Adds flower to liveData and posts value. */
    // headers: List<Pair<String, String>>
    fun  HttpGet(parameters : List<String>, url: String = apiURL): Result<String?> {
        var finalUrl: String = url
        for (param in parameters){
            finalUrl = finalUrl + param + "/"
        }
        val request = Request.Builder()
            .addHeader("x-rapidapi-host","random-palette-generator.p.rapidapi.com")
            .addHeader("x-rapidapi-key",apiKey)
            .url(finalUrl).build()
            val response = client.newCall(request).execute()
        return if (response != null)
            Result.success(response.body()?.string())
        else
            return Result.failure(Exception("Err"))
        }

}