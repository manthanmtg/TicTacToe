package com.example.tic_tac_toe

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    // 0: yellow, 1: red, 2: empty
    var gameState = intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
    var winningPositions = arrayOf(intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8), intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8), intArrayOf(0, 4, 8), intArrayOf(2, 4, 6))
    var activePlayer = 0
    var gameActive = true
    @SuppressLint("SetTextI18n")
    fun dropIn(view: View) {
        val counter: ImageView = view as ImageView
        val tappedCounter = counter.tag.toString().toInt()
        if (gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activePlayer
            counter.translationY = -1500f
            activePlayer = if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow)
                1
            } else {
                counter.setImageResource(R.drawable.red)
                0
            }
            counter.animate().translationYBy(1500f).rotation(3600f).duration = 300
            for (winningPosition in winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    gameActive = false
                    var winner = ""
                    winner = if (activePlayer == 1) {
                        "Yellow"
                    } else {
                        "Red"
                    }
                    val playAgainButton: Button = findViewById(R.id.playAgainButton)
                    val winnerTextView:TextView = findViewById(R.id.winnerTextView)
                    winnerTextView.text = "$winner has won!"
                    playAgainButton.visibility = View.VISIBLE
                    winnerTextView.visibility = View.VISIBLE
                }
            }
        }
    }

    fun playAgain(view: View?) {
        val playAgainButton: Button = findViewById(R.id.playAgainButton)
        val winnerTextView : TextView= findViewById(R.id.winnerTextView)
        playAgainButton.visibility = View.INVISIBLE
        winnerTextView.visibility = View.INVISIBLE
        val gridLayout: GridLayout = findViewById(R.id.gridLayout)
        for (i in 0 until gridLayout.childCount) {
            val counter: ImageView = gridLayout.getChildAt(i) as ImageView
            counter.setImageDrawable(null)
        }
        for (i in gameState.indices) {
            gameState[i] = 2
        }
        activePlayer = 0
        gameActive = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}