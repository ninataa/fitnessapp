package com.example.android.roomwordssample

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.observe
import com.example.android.roomwordssample.databinding.ActivityUserProfileBinding


class UserProfile : AppCompatActivity() {
    private  lateinit var binding: ActivityUserProfileBinding

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as Application).repository)
    }
    private val favExerciseViewModel: FavExerciseViewModel by viewModels {
        FavExerciseViewModelFactory((application as Application).favExerciseRepository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nav.setOnItemSelectedListener {it ->
            when(it.itemId){
                R.id.profile -> {
                    true
                }
                R.id.plan -> {
                    val intent = Intent(this@UserProfile, PersonalPlan::class.java)
                    startActivity(intent)
                    true}
                R.id.all -> {
                    val intent = Intent(this@UserProfile, AllExercises::class.java)
                    startActivity(intent)
                    true}
                else -> false
            }
        }

        binding.nav.selectedItemId = R.id.profile
        val name = findViewById<TextView>(R.id.username)
        val gender = findViewById<TextView>(R.id.gender)
        val height = findViewById<TextView>(R.id.height)
        val weight = findViewById<TextView>(R.id.weight)
        val bmi = findViewById<TextView>(R.id.BMI)
        val gainmuscle = findViewById<ImageView>(R.id.gainmusclecheck)
        val leanandtone = findViewById<ImageView>(R.id.leanandtonecheck)
        val endurance = findViewById<ImageView>(R.id.endurancecheck)
        val loosefat = findViewById<ImageView>(R.id.loosefatcheck)

        userViewModel.allWords.observe(owner = this) {
            if(it.size > 0) {
                val it = it[0]
                name.text = it.name
                gender.text = it.gender
                height.text = it.height.toString()
                weight.text = it.weight.toString()

                val num : Float
                val heightInM = it.height.toFloat()/100
                num = it.weight.toFloat()/(heightInM*heightInM)
                bmi.text = String.format("%.2f", num)

                if(it.gain_muscle) gainmuscle.setImageResource(R.drawable.baseline_check_circle_24)
                if(it.lean_and_tone) leanandtone.setImageResource(R.drawable.baseline_check_circle_24)
                if(it.endurance) endurance.setImageResource(R.drawable.baseline_check_circle_24)
                if(it.loose_fat) loosefat.setImageResource(R.drawable.baseline_check_circle_24)
            }
        }


        val logOut = findViewById<TextView>(R.id.logout)
        logOut.setOnClickListener{
//            wordViewModel.delete()
//            val intent = Intent(this@UserProfile, MainActivity::class.java)
//            startActivity(intent)
            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.dialog_layout, null)
            val yesButton = dialogLayout.findViewById<Button>(R.id.yes)
            val noButton = dialogLayout.findViewById<Button>(R.id.no)
            var result = ""
            val dialog = builder.setView(dialogLayout).create()

            yesButton.setOnClickListener {
                userViewModel.delete()
                favExerciseViewModel.deleteAllExercise()
                val intent = Intent(this@UserProfile, MainActivity::class.java)
                startActivity(intent)
                dialog.dismiss()
            }

            noButton.setOnClickListener {
                result = "no"
                dialog.dismiss()
            }
            dialog.show()
        }
    }
}