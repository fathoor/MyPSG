package com.dicoding.mypsg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvPlayers: RecyclerView
    private val list = ArrayList<Player>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnProfile: ImageView = findViewById(R.id.ic_user)
        btnProfile.setOnClickListener {
            val profileIntent = Intent(this@MainActivity, ProfileActivity::class.java)
            startActivity(profileIntent)
        }

        rvPlayers = findViewById(R.id.rv_players)
        rvPlayers.setHasFixedSize(true)

        list.addAll(getListPlayers())
        showRecyclerList()
    }

    private fun showRecyclerList() {
        rvPlayers.layoutManager = GridLayoutManager(this, 2)
        val listPlayerAdapter = PlayerAdapter(list)
        rvPlayers.adapter = listPlayerAdapter

        listPlayerAdapter.setOnCardClickCallback(object : PlayerAdapter.OnCardClickCallback {
            override fun onCardClicked(data: Player) {
                showSelectedPlayer(data)
            }
        })
    }

    private fun showSelectedPlayer(player: Player) {
        val playerIntent = Intent(this@MainActivity, DetailActivity::class.java)
        playerIntent.putExtra(DetailActivity.EXTRA_PLAYER, player)
        startActivity(playerIntent)
    }

    private fun getListPlayers(): ArrayList<Player> {
        val playerName = resources.getStringArray(R.array.player_name)
        val playerShortName = resources.getStringArray(R.array.player_short_name)
        val playerPosition = resources.getStringArray(R.array.player_position)
        val playerNumber = resources.getIntArray(R.array.player_number)
        val playerNationality = resources.getStringArray(R.array.player_nationality)
        val playerBio = resources.getStringArray(R.array.player_bio)
        val playerPhoto = resources.getStringArray(R.array.player_photo)

        val listPlayerData = ArrayList<Player>()
        for (i in playerName.indices) {
            val player = Player(
                playerName[i],
                playerShortName[i],
                playerPosition[i],
                playerNumber[i],
                playerNationality[i],
                playerBio[i],
                playerPhoto[i]
            )
            listPlayerData.add(player)
        }
        return listPlayerData
    }
}