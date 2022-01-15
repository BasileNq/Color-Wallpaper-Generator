package com.example.colorwallpapergenerator.utils

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import org.json.JSONTokener
import java.lang.Exception

class Requester {

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
            .addHeader("x-rapidapi-key","a7e5a0cf67msh654b69d77b40c90p12d2fbjsnc0c9c6213604")
            .url(finalUrl).build()
            val response = client.newCall(request).execute()
        return if (response != null)
            Result.success(response.body()?.string())
        else
            return Result.failure(Exception("Err"))
        }

}