package com.example.android.roomwordssample

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ExerciseDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_detail)

        val recentExercise = intent.getParcelableExtra<Exercise>("Exercise")

        val exerciseName = findViewById<TextView>(R.id.name)
        val eSet = findViewById<TextView>(R.id.set)
        val eDescription = findViewById<TextView>(R.id.desc)
        val backButton = findViewById<TextView>(R.id.back)

        recentExercise?.let{
            exerciseName.text = it.exercise_name
            eSet.text = it.set
            eDescription.text = it.description
            backButton.setOnClickListener{
                this.finish()
            }
        }
    }
}