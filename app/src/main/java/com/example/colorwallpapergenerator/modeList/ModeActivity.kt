package com.example.colorwallpapergenerator.modeList

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.colorwallpapergenerator.R
import com.example.colorwallpapergenerator.databinding.ActivityMainBinding


class ModeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_main)

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerView)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class Mode
        val modelModes: ViewModelMode by viewModels()
        val adapter = MainAdapter(modelModes.getModes())
        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

    }

}