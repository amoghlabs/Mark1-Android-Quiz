package com.example.mark1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ResultActivity : AppCompatActivity() {

    private lateinit var scoreText : TextView
    private lateinit var messageText : TextView
    private lateinit var playAgainButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_result)

        scoreText = findViewById(R.id.scoreText)
        messageText = findViewById(R.id.messageText)
        playAgainButton = findViewById(R.id.playAgainButton)

        val score = intent.getIntExtra("SCORE",0)
        val total = intent.getIntExtra("TOTAL",0)
        scoreText.text = "$score/$total"

        messageText.text = when {
            score == total -> "Perfect"
            score >= total/2 -> "Good job"
            else -> "good try"
        }
        playAgainButton.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}