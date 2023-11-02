package com.example.android.roomwordssample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.android.roomwordssample.databinding.ActivityAllExercisesBinding
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.navigation.NavigationBarMenu

class AllExercises : AppCompatActivity() {
private  lateinit var binding: ActivityAllExercisesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_exercises)
        binding = ActivityAllExercisesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nav.setOnItemSelectedListener {it ->
            when(it.itemId){
                R.id.profile -> {
                    val intent = Intent(this@AllExercises, UserProfile::class.java)
                    startActivity(intent)
                    true
                }
                R.id.plan -> {
                    val intent = Intent(this@AllExercises, PersonalPlan::class.java)
                    startActivity(intent)
                    true}
                R.id.all -> { true}
                else -> false
            }
        }

        binding.nav.selectedItemId = R.id.all

        val fullButton = findViewById<Button>(R.id.full)
        fullButton.setOnClickListener {
            val intent = Intent(this@AllExercises, GroupExercises::class.java)
            intent.putExtra("Group", "Full Body")
            startActivity(intent)
        }

        val lowerButton = findViewById<Button>(R.id.lower)
        lowerButton.setOnClickListener {
            val intent = Intent(this@AllExercises, GroupExercises::class.java)
            intent.putExtra("Group", "Lower Body")
            startActivity(intent)
        }

        val upperButton = findViewById<Button>(R.id.upper)
        upperButton.setOnClickListener {
            val intent = Intent(this@AllExercises, GroupExercises::class.java)
            intent.putExtra("Group", "Upper Body")
            startActivity(intent)
        }

        val abButton = findViewById<Button>(R.id.ab)
        abButton.setOnClickListener {
            val intent = Intent(this@AllExercises, GroupExercises::class.java)
            intent.putExtra("Group", "Ab")
            startActivity(intent)
        }


    }
    fun replaceActivity(string: String)
    {
        val intent = Intent(this@AllExercises, string::class.java)
        startActivity(intent)
    }
}