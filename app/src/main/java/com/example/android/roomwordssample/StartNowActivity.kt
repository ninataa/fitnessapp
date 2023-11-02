package com.example.android.roomwordssample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.observe

class StartNowActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as Application).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startnow)

        val name = findViewById<TextView>(R.id.mess)
        userViewModel.allWords.observe(owner = this) { word ->
            name.text = "Welcome, " + word[0].name + "!"
        }

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val intent = Intent(this@StartNowActivity, AllExercises::class.java)
            startActivity(intent)
        }
    }
}