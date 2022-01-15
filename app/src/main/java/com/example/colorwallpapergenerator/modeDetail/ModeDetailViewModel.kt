package com.example.colorwallpapergenerator.modeDetail

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.colorwallpapergenerator.datas.ColorService
import com.example.colorwallpapergenerator.datas.Wallpaper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ModeDetailViewModel(private val datas: ColorService) : ViewModel() {

    val currentWallpaper = MutableLiveData<Wallpaper>()

    fun getColor(name: String): Wallpaper? {
        var wallpaper: Wallpaper? = null
        viewModelScope.launch(Dispatchers.IO) {
                wallpaper = datas.getColors(name)
                withContext(Dispatchers.Main){
                    currentWallpaper.postValue(wallpaper!!)
                }
        }
        return wallpaper
    }

    fun setWallpaper(context: Context){
        val bmp : Bitmap = Bitmap.createBitmap(500,500, Bitmap.Config.ARGB_8888 )
        val canva: Canvas = Canvas(bmp)
        val ptn: Paint = Paint()
        val ptn2: Paint = Paint()
        val ptn3: Paint = Paint()
        try {
            ptn.setColor(Color.parseColor(currentWallpaper.value?.data?.get(0)?.palette?.get(0)))
            ptn2.setColor(Color.parseColor(currentWallpaper.value?.data?.get(0)?.palette?.get(1)))
            ptn3.setColor(Color.parseColor(currentWallpaper.value?.data?.get(0)?.palette?.get(2)))
            canva.drawRect(0f, 0f, 300f, 150f, ptn)
            canva.drawRect(0f, 150f, 300f, 300f, ptn2)
            canva.drawRect(0f, 300f, 300f, 450f, ptn3)
            val wpm = WallpaperManager.getInstance(context)
            wpm.setBitmap(bmp)
        } catch (e: Exception){
            e.printStackTrace()
        }
        //left top right bottom

    }
}

class ModeDetailViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ModeDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ModeDetailViewModel(
                datas = ColorService.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}