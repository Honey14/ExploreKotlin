package com.honey.timefighter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    internal var score = 0
    internal val initialCountDown: Long = 10000
    internal var timeLeftOnTimer: Long = 10000
    internal val countDownInterval: Long = 1000
    internal lateinit var countDownTimer: CountDownTimer
    internal var gameStarted = false

    companion object {
        private val SCORE_KEY = "SCORE_KEY"
        private val TIME_LEFT_KEY = "TIME_LEFT_KEY"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        if(savedInstanceState !=null){
            score = savedInstanceState.getInt(SCORE_KEY)
            timeLeftOnTimer = savedInstanceState.getLong(TIME_LEFT_KEY)
            restoreGame()
        }else{
            resetGame()
        }
        button_tap.setOnClickListener {
            incrementScore()
        }
    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SCORE_KEY, score)
        outState.putLong(TIME_LEFT_KEY, timeLeftOnTimer)
        countDownTimer.cancel()
    }

    private fun restoreGame() {
        button_tap.setImageDrawable(getDrawable(R.drawable.ic_game_controller))
        var score_received = "$score"
        textView_score.text = score_received
        var restoredTime = "Time Left : " + timeLeftOnTimer / 1000
        text_timer.text = restoredTime
        countDownTimer = object : CountDownTimer(timeLeftOnTimer,countDownInterval){
            override fun onTick(millisUntilFinished: Long) {
                timeLeftOnTimer = millisUntilFinished

                var timeLeft = "Time Left : " + millisUntilFinished/1000
                text_timer.text = timeLeft
            }

            override fun onFinish() {
                endGame()
            }
        }
    }
    private fun resetGame() {
        score = 0
        button_tap.setImageDrawable(getDrawable(R.drawable.ic_play))
        circularProgressBar.progress = 0F
        textView_score.text = score.toString()
        var initialtimeLeft = initialCountDown / 1000
        text_timer.text = String.format(getString(R.string.time_left), initialtimeLeft)
        var increase_progress = 50
        countDownTimer = object : CountDownTimer(initialCountDown, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftOnTimer = millisUntilFinished

                var timeleft = millisUntilFinished / 1000
                val timestring = "Time Left: $timeleft"
                text_timer.text = timestring
//                circularProgressBar.progress = ((initialCountDown-millisUntilFinished)/initialCountDown*100.0).toFloat()

//                circularProgressBar.setProgressWithAnimation(increase_progress.toFloat(),10000)
                val baseRadius = 20 // base radius is basic radius of circle from which to start animation
                var custpom = CustomView(this@MainActivity)
                custpom.updateView(increase_progress + baseRadius)
                increase_progress++
            }

            override fun onFinish() {
                increase_progress++
                endGame()
            }
        }
        gameStarted = false
    }

    private fun startGame() {
        countDownTimer.start()
        gameStarted = true
    }

    private fun endGame() {
        var gameOver = "Game Over. Your score is $score"
        Toast.makeText(this, gameOver, Toast.LENGTH_LONG).show()
        resetGame()
    }

    private fun incrementScore() {
        if (!gameStarted) {
            startGame()
        }
        score = score + 1
        val newScore = "$score"
        textView_score.text = newScore
        button_tap.setImageDrawable(getDrawable(R.drawable.ic_game_controller))

    }
}


//IDEA
//balls ladki me rahenge
//niche list of dabba gumte rahega,
//timer hoga
//button to put balls inside dabba
//
//On click of the button, balls niche girenge and number of balls count hoga jo badda me jaega
//
//Step 1
//--> Make UI
//Step 2
//--> Give 50 coins when first time
//Step 3
//--> Click on start button, deduct 25 coins to play game
//Step 4
//--> Click on start button, start the timer from 60 seconds
//Step 5
//--> Click on start button, Dabba ko circular me gumate raho
//Step 6
//--> Handling balls in dabba and how naturally they can fall inside and outside the dabba
//Step 7
//--> Earn coins
//--> Above 50 balls in 60 seconds == 100 coins
//--> Above 35 balls in 60 seconds == 60 coins
//--> Above 30 balls in 60 seconds == 40 coins
//--> Above 15 coins in 60 seconds == 25 coins