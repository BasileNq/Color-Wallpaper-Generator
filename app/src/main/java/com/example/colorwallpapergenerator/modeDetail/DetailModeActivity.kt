package com.example.colorwallpapergenerator.modeDetail

import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import com.example.colorwallpapergenerator.R
import java.nio.channels.FileLock


class DetailModeActivity : AppCompatActivity() {
    private val modeDetailViewModel by viewModels<ModeDetailViewModel> {
        ModeDetailViewModelFactory(this)
    }
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_view_desgin_detail)
        val screenSize: Pair<Int, Int> = modeDetailViewModel.getScreenSize()


        val textView: TextView = findViewById(R.id.textview_detail)
        val imageView: ImageView = findViewById(R.id.imageview_detail)
        val generateButton: Button = findViewById(R.id.generate_button)
        val title: TextView = findViewById(R.id.title)
        val global: CardView = findViewById(R.id.global)
        val color1: View = findViewById(R.id.color_1)
        val color2: View = findViewById(R.id.color_2)
        val color3: View = findViewById(R.id.color_3)
        val wallpaperButton: Button = findViewById(R.id.wallpaper_button)

        //set image size
        global.layoutParams.height = screenSize.second
        //set generated colors sizes`
        color1.layoutParams.height = screenSize.first/3
        color1.layoutParams.width = screenSize.first/3
        color2.layoutParams.height = screenSize.first/3
        color2.layoutParams.width = screenSize.first/3
        color3.layoutParams.height = screenSize.first/3
        color3.layoutParams.width = screenSize.first/3


        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            textView.text = bundle.getString("text")
            title.text = bundle.getString("text")
            imageView.id = bundle.getInt("image")
            imageView.setImageResource(imageView.id)
        }
        modeDetailViewModel.currentWallpaper.observe(this, Observer {
            color1.setBackgroundColor(Color.parseColor(it.data[0].palette[0]))
            color2.setBackgroundColor(Color.parseColor(it.data[0].palette[1]))
            color3.setBackgroundColor(Color.parseColor(it.data[0].palette[2]))
        })



        generateButton.setOnClickListener{
            modeDetailViewModel.getColor(textView.text as String)
        }
        wallpaperButton.setOnClickListener{
            modeDetailViewModel.setWallpaper(this.applicationContext)
        }

    }
}