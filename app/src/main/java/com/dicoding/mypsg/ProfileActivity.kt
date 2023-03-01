package com.dicoding.mypsg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val btnBack: ImageView = findViewById(R.id.ic_previous)
        btnBack.setOnClickListener {
            finish()
        }
    }
}