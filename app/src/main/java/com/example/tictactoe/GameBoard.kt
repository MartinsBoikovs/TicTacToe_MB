package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.os.Handler
import android.view.View
import com.example.tictactoe.R


class GameBoard : AppCompatActivity() {
    object turn {  //Counter to count moves for calculating which player is making move
        var counter = 1
    }
    private val handler = Handler() //Handler to pause a little bit computer move for game to be smoother

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)  //Default onCreate code
        setContentView(R.layout.activity_game_board2)

        val exit = findViewById<Button>(R.id.exit)
        exit.setOnClickListener {
            finishAffinity()  //Exit from app on exit Button
            turn.counter = 1
        }

        val restart = findViewById<Button>(R.id.restart)
        restart.setOnClickListener {  //Restart button restarting all the app -> automatically going to home screen (MainActivity)
            val Home = Intent(this, MainActivity::class.java)
            startActivity(Home)
        }

        val first = intent.getStringExtra("1st").toString()  //Passing info about player names and outputting it on game layout
        val second = intent.getStringExtra("2nd").toString()

        findViewById<TextView>(R.id.p1).setText(first)
        findViewById<TextView>(R.id.p2).setText(second)

        val buttonId = intent.getStringExtra("buttonID").toString() //Getting info about move

        val again = findViewById<Button>(R.id.again)
        again.setOnClickListener {
            val newGame = Intent(this, GameBoard::class.java) //Button play again onClick function
            newGame.putExtra("buttonID", buttonId)
            newGame.putExtra("1st", first) // Pass  player's name and mode info not to lose that info restarting this activity
            newGame.putExtra("2nd", second)
            turn.counter = 1
            startActivity(newGame)
        }

        //TODO: implement turn for computer
        //TODO: implement logic for "Your Turn" notification as well as logic for game winners according to what buttons has X or O.
        val one = findViewById<Button>(R.id.one)
        val two = findViewById<Button>(R.id.two)
        val three = findViewById<Button>(R.id.three)
        val four = findViewById<Button>(R.id.four)
        val five = findViewById<Button>(R.id.five)
        val six = findViewById<Button>(R.id.six)
        val seven = findViewById<Button>(R.id.seven)    //game board buttons
        val eight = findViewById<Button>(R.id.eight)
        val nine = findViewById<Button>(R.id.nine)

        val buttons = arrayOf(one, two, three, four, five, six, seven, eight, nine)

        runGame(one, buttons, first, second, buttonId)
        runGame(two, buttons, first, second, buttonId)
        runGame(three, buttons, first, second, buttonId)
        runGame(four, buttons, first, second, buttonId)
        runGame(five, buttons, first, second, buttonId)
        runGame(six, buttons, first, second, buttonId) // Function to make buttons act
        runGame(seven, buttons, first, second, buttonId)
        runGame(eight, buttons, first, second, buttonId)
        runGame(nine, buttons, first, second, buttonId)
    }

    fun TicTac(): String {  // Method to choose what is outputting to button O or X according to turn counter
        if (turn.counter % 2 ==1) {
            return ("❌")
        } else {
            return ("⭕")
        }
    }

    fun yourTurn() { //Just repplacing your Turn image
        if (turn.counter % 2 ==1) {
            findViewById<TextView>(R.id.turn1).setText("Your Turn!")
            findViewById<TextView>(R.id.turn2).setText("")
        } else {
            findViewById<TextView>(R.id.turn2).setText("Your Turn!")
            findViewById<TextView>(R.id.turn1).setText("")
        }
    }

    fun runGame(area: Button, buttons: Array<Button>, first: String, second: String, mode: String) { //Main function that provides all actions

        area.setOnClickListener {  //On current button clicking function, that calls TicTac() method to output x/O
            findViewById<TextView>(R.id.counter).setText(turn.counter.toString())
            if (area.text != "") { //Checking if the button was clicked already
            } else {
                area.text = TicTac()
                turn.counter++
                yourTurn()
                checkResult(buttons, first, second) //Calling that function after every button clicking to check game end everytime
                if (mode == "pvc" && turn.counter % 2 == 0){ // If mode is pvc we are genering computer move
                    computerMove()
                }
            }
        }
    }

    fun checkResult(btns: Array<Button>, first: String, second: String) {   //Result checking by checking if the apropriate buttons are klicked with X or O value.
        if (btns[0].text == "❌" && btns[1].text == "❌" && btns[2].text == "❌" ||
            btns[3].text == "❌" && btns[4].text == "❌" && btns[5].text == "❌" ||
            btns[6].text == "❌" && btns[7].text == "❌" && btns[8].text == "❌" ||
            btns[0].text == "❌" && btns[3].text == "❌" && btns[6].text == "❌" ||
            btns[1].text == "❌" && btns[4].text == "❌" && btns[7].text == "❌" ||
            btns[2].text == "❌" && btns[5].text == "❌" && btns[8].text == "❌" ||
            btns[0].text == "❌" && btns[4].text == "❌" && btns[8].text == "❌" ||
            btns[2].text == "❌" && btns[4].text == "❌" && btns[6].text == "❌") {
            val message = first + " wins!!!"
            findViewById<TextView>(R.id.result).setText(message) //just winner messages
            findViewById<TextView>(R.id.turn1).setText("")
            findViewById<TextView>(R.id.turn2).setText("")
        } else if (btns[0].text == "⭕" && btns[1].text == "⭕" && btns[2].text == "⭕" ||
            btns[3].text == "⭕" && btns[4].text == "⭕" && btns[5].text == "⭕" ||
            btns[6].text == "⭕" && btns[7].text == "⭕" && btns[8].text == "⭕" ||
            btns[0].text == "⭕" && btns[3].text == "⭕" && btns[6].text == "⭕" ||
            btns[1].text == "⭕" && btns[4].text == "⭕" && btns[7].text == "⭕" ||
            btns[2].text == "⭕" && btns[5].text == "⭕" && btns[8].text == "⭕" ||
            btns[0].text == "⭕" && btns[4].text == "⭕" && btns[8].text == "⭕" ||
            btns[2].text == "⭕" && btns[4].text == "⭕" && btns[6].text == "⭕") {
            val message = second + " wins!!!"
            findViewById<TextView>(R.id.result).setText(message)
            findViewById<TextView>(R.id.turn1).setText("")
            findViewById<TextView>(R.id.turn2).setText("")
        } else if (turn.counter == 10) { //If all the button on the board were clicked and there was not winner, its a draw
            val message = "Draw:("
            findViewById<TextView>(R.id.result).setText(message)
            findViewById<TextView>(R.id.turn1).setText("")
            findViewById<TextView>(R.id.turn2).setText("")
        }
        if (findViewById<TextView>(R.id.result).text != "") {  //Disabling all the board buttons
            btns[0].isEnabled = false
            btns[1].isEnabled = false
            btns[2].isEnabled = false
            btns[3].isEnabled = false
            btns[4].isEnabled = false
            btns[5].isEnabled = false
            btns[6].isEnabled = false
            btns[7].isEnabled = false
            btns[8].isEnabled = false
            turn.counter = 1
        }
    }

    fun computerMove() { //Computer move generation, using just Random (I could make logic with gameTree, Minimax algorythm f.e.,  but it takes too long time:((, as we done it on another course)
        val btn = (0..8).random()
        val id = when (btn) {  //Switch to see which button is generated to click
            0 -> findViewById<Button>(R.id.one)
            1 -> findViewById<Button>(R.id.two)
            2 -> findViewById<Button>(R.id.three)
            3 -> findViewById<Button>(R.id.four)
            4 -> findViewById<Button>(R.id.five)
            5 -> findViewById<Button>(R.id.six)
            6 -> findViewById<Button>(R.id.seven)
            7 -> findViewById<Button>(R.id.eight)
            8 -> findViewById<Button>(R.id.nine)
            else -> findViewById<Button>(R.id.one)
        }

        if (id.text == "") { //Chechikng if the generated button was already clicked, if yes recursivly generate new button
            handler.postDelayed({id.performClick()}, 400) //Handling a little bit computer click perfromance for game too be smoother
        } else {
            computerMove() //new button generating
        }
    }

}
