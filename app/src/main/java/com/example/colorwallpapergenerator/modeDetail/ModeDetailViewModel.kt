package com.example.colorwallpapergenerator.modeDetail

import android.app.WallpaperManager
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.*
import android.os.Build
import android.view.Display
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.colorwallpapergenerator.datas.ColorService
import com.example.colorwallpapergenerator.datas.Wallpaper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.util.DisplayMetrics




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

    fun getScreenSize(): Pair<Int, Int>{
        val config: Configuration = Resources.getSystem().configuration
        return Pair(config.screenWidthDp, config.screenHeightDp)
    }
    fun setWallpaper(context: Context){
        val screenSize: Pair<Int, Int> = getScreenSize()
        val screenWidthInPixels = screenSize.first
        val screenHeightInPixels = screenSize.second
        val bmp : Bitmap = Bitmap.createBitmap(screenWidthInPixels,screenHeightInPixels, Bitmap.Config.ARGB_8888 )
        val canva: Canvas = Canvas(bmp)
        val ptn: Paint = Paint()
        val ptn2: Paint = Paint()
        val ptn3: Paint = Paint()
        try {
            val screenWidthInFloat: Float = screenWidthInPixels.toFloat()
            val screenHeightInFloat: Float = screenHeightInPixels.toFloat()
            ptn.setColor(Color.parseColor(currentWallpaper.value?.data?.get(0)?.palette?.get(0)))
            ptn2.setColor(Color.parseColor(currentWallpaper.value?.data?.get(0)?.palette?.get(1)))
            ptn3.setColor(Color.parseColor(currentWallpaper.value?.data?.get(0)?.palette?.get(2)))
            canva.drawRect(0f, 0f, screenWidthInFloat, screenHeightInFloat/ 3, ptn)
            canva.drawRect(0f, screenHeightInFloat/3, screenWidthInFloat, 2*screenHeightInFloat/3, ptn2)
            canva.drawRect(0f, 2*screenHeightInFloat/3, screenWidthInFloat, screenHeightInFloat, ptn3)
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