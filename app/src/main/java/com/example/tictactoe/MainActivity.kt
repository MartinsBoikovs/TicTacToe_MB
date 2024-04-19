package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.tictactoe.R

//All the programm is written by myself (Martins Boikovs). I was using stackoverflow.com for checking xml coding issues, f.e., button colors and other. Also I asked ChatGPT to help with button colors,
// but he could provide me some good solution o the problem, thats why i was going to stackoverflow. Also I was checking some videos of indian people to see hoow to work with Adnroid studio,
// but due to the fact, that was indian people, I couldnt understand anything :).
// Everything else was made only by myself.

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val players = findViewById<Button>(R.id.pvp)
        players.setOnClickListener {  //Button logic, to go to next activity layout passing choosen game mode
            val names = Intent(this, NameSelect::class.java)
            names.putExtra("buttonID", "pvp")
            startActivity(names)
        }

        val player = findViewById<Button>(R.id.pvc)
        player.setOnClickListener {  //Button logic, to go to next activity layout passing choosen game mode
            val names = Intent(this, NameSelect::class.java)
            names.putExtra("buttonID", "pvc")
            startActivity(names)
        }
    }


}