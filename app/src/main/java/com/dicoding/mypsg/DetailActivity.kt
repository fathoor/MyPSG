package com.dicoding.mypsg

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_PLAYER = "extra_player"
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val name: TextView = findViewById(R.id.tv_player_name)
        val shortName: TextView = findViewById(R.id.tv_player_short_name)
        val position: TextView = findViewById(R.id.tv_player_position)
        val number: TextView = findViewById(R.id.tv_player_number)
        val nationality: TextView = findViewById(R.id.tv_player_nationality)
        val bio: TextView = findViewById(R.id.tv_player_bio)
        val photo: ImageView = findViewById(R.id.img_player)

        val playerData = intent.getParcelableExtra<Player>(EXTRA_PLAYER) as Player
        name.text = playerData.name
        shortName.text = playerData.shortName
        position.text = playerData.position
        number.text = playerData.number.toString()
        nationality.text = playerData.nationality
        bio.text = playerData.bio
        Glide.with(this).load(playerData.photo).into(photo)

        val btnPrevious: ImageView = findViewById(R.id.ic_previous)
        btnPrevious.setOnClickListener {
            finish()
        }

        val btnShare: ImageView = findViewById(R.id.action_share)
        btnShare.setOnClickListener {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Wow! ${playerData.name} will be playing as starter on the upcoming PSG match! Can't wait to see him live with his number ${playerData.number}!")
            shareIntent.type = "text/plain"
            startActivity(Intent.createChooser(shareIntent, "Share to:"))
        }
    }
}