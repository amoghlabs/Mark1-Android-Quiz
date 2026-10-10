package com.example.mark1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var questionText : TextView
    private lateinit var questionNumberText : TextView
    private lateinit var optionsGroup : RadioGroup
    private lateinit var nextButton : Button

    private val questions = arrayOf(
        "Which language is used for Android development?",
        "Which IDE is commonly used for Android development?",
        "What does APK stands for",
        "Which data structure follow FIFO"
    )

    private val options = arrayOf(
        arrayOf("Go","Kotlin","Python","C++"),
        arrayOf("Android Studio","IntelliJ","Visual Studio","Eclipse"),
        arrayOf("Android Package Kit", "Android Project Kit", "Android Package File", "Android Project File"),
        arrayOf("Stack","Queue","Tree","Linked List")
    )

    private val correctAnswers = intArrayOf(
        1,0,0,1
    )
    private var currentQuestion = 0
    private var score = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        questionText = findViewById(R.id.questionText)
        questionNumberText = findViewById(R.id.questionNumberText)
        optionsGroup = findViewById(R.id.optionsGroup)
        nextButton = findViewById(R.id.nextButton)

        showQuestion()

        nextButton.setOnClickListener {
            val answered = checkAnswer()
            if (!answered){
                return@setOnClickListener
            }

            if (currentQuestion < questions.size - 1){
                currentQuestion++
                showQuestion()
            } else{
                openResultScreen()
            }
        }
    }

    private fun showQuestion(){

        questionText.text = questions[currentQuestion]
        questionNumberText.text = "${currentQuestion + 1}/${questions.size}"

        val radioButtons = arrayOf(
            findViewById<RadioButton>(R.id.option1),
            findViewById<RadioButton>(R.id.option2),
            findViewById<RadioButton>(R.id.option3),
            findViewById<RadioButton >(R.id.option4)
        )

        for (i in radioButtons.indices){
            radioButtons[i].text = options[currentQuestion][i]
        }
        optionsGroup.clearCheck()
    }

    private fun checkAnswer() : Boolean {
        val selectedId = optionsGroup.checkedRadioButtonId
        if (selectedId == -1){
            Toast.makeText(this,"Select an Answer",Toast.LENGTH_SHORT)
                .show()

            return false
        }

        val selectedButton = findViewById<RadioButton>(selectedId)
        val selectedIndex = options[currentQuestion].indexOf(
            selectedButton.text.toString()
        )
        if (selectedIndex == correctAnswers[currentQuestion]){
            score++

            Toast.makeText(this,"Correct",Toast.LENGTH_SHORT).show()

        } else{
            Toast.makeText(this,"Wrong, Correct Answer: ${options[currentQuestion][correctAnswers[currentQuestion]]}",Toast.LENGTH_SHORT).show()

        }
        return true
    }

    private fun openResultScreen(){
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("SCORE", score)
        intent.putExtra("TOTAL", questions.size)
        startActivity(intent)

        finish()
    }
}