package com.example.colorwallpapergenerator.modeList

import androidx.lifecycle.ViewModel
import com.example.colorwallpapergenerator.R
import com.example.colorwallpapergenerator.datas.Mode


class ViewModelMode : ViewModel() {
    private val modes: MutableList<Mode> = loadModes()


    fun getModes(): List<Mode> {
        return modes
    }

    private fun loadModes(): MutableList<Mode> {
        return mutableListOf(
            Mode("Monochromatic", R.drawable.monochromatic),
            Mode("Square", R.drawable.square),
            Mode("Triade",R.drawable.triade),
            //Mode("Shade",R.drawable.shade),
            Mode("Complementary", R.drawable.complementary),
        )
    }
}