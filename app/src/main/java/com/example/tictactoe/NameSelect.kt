package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.tictactoe.R

class NameSelect : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name_select)

        val buttonId = intent.getStringExtra("buttonID") //Getting passed info abput mode
        // Now you can check which button was clicked based on the buttonID
        if (buttonId == "pvc") {   // If choosen mode pav pvc, name for other player set to Computer
            findViewById<EditText>(R.id.second).setText("Computer")
            findViewById<EditText>(R.id.second).isEnabled = false
        }

        val game = findViewById<Button>(R.id.startGame)
        game.setOnClickListener {   //Button Start Game logic, to go to next activity layout -> GameBoard.xml and .kt
            val firstPlayerName = findViewById<EditText>(R.id.first).text.toString()
            val secondPlayerName = findViewById<EditText>(R.id.second).text.toString()

            val start = Intent(this, GameBoard::class.java)
            start.putExtra("buttonID", buttonId)   //also passing mode and players names
            start.putExtra("1st", firstPlayerName)
            start.putExtra("2nd", secondPlayerName)
            startActivity(start)
        }


    }
}
