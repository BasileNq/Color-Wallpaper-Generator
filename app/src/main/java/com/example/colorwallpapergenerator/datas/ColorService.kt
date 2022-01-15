package com.example.colorwallpapergenerator.datas
import android.content.res.Resources
import com.example.colorwallpapergenerator.utils.Requester
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ColorService(resources: Resources) {

    /* Adds flower to liveData and posts value. */
    suspend fun getColors(mode: String): Wallpaper {
        var params: ArrayList<String> = ArrayList<String>()
        params.add(mode)
        params.add("1")
        params.add("3")
        return withContext(Dispatchers.IO) {
            val rq =  Requester()
            val response: Result<String?> = rq.HttpGet(params)
            if (response.isSuccess){
                return@withContext parseColors(response.getOrNull())
            } else
                return@withContext Wallpaper("Monochromatique", ArrayList())
        }
    }

    private fun parseColors(response: String?): Wallpaper {
        return Gson().fromJson(response, Wallpaper::class.java)
    }


    companion object {
        private var INSTANCE: ColorService? = null

        fun getDataSource(resources: Resources): ColorService {
            return synchronized(ColorService::class) {
                val newInstance = INSTANCE ?: ColorService(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}