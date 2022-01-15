package com.example.colorwallpapergenerator.modeDetail

import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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


        val textView: TextView = findViewById(R.id.textview_detail)
        val imageView: ImageView = findViewById(R.id.imageview_detail)
        val generate_button: Button = findViewById(R.id.generate_button)
        //val generated_colors: TextView = findViewById(R.id.generated_colors)
        val color_1: View = findViewById(R.id.color_1)
        val color_2: View = findViewById(R.id.color_2)
        val color_3: View = findViewById(R.id.color_3)
        val wallpaper_button: Button = findViewById(R.id.wallpaper_button)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            textView.text = bundle.getString("text")
            imageView.id = bundle.getInt("image")
            imageView.setImageResource(imageView.id)
        }
        modeDetailViewModel.currentWallpaper.observe(this, Observer {
            //generated_colors.text = it.data[0].palette.toString()
            color_1.setBackgroundColor(Color.parseColor(it.data[0].palette[0]))
            color_2.setBackgroundColor(Color.parseColor(it.data[0].palette[1]))
            color_3.setBackgroundColor(Color.parseColor(it.data[0].palette[2]))
        })



        generate_button.setOnClickListener{
            modeDetailViewModel.getColor(textView.text as String)
        }
        wallpaper_button.setOnClickListener{
            System.out.println("erdftg")
            modeDetailViewModel.setWallpaper(this.applicationContext)
        }

    }
}