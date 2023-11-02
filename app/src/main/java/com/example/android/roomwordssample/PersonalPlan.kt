package com.example.android.roomwordssample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.roomwordssample.databinding.ActivityPersonalPlanBinding


class PersonalPlan : AppCompatActivity() {
    private  lateinit var binding: ActivityPersonalPlanBinding

    private lateinit var exerciseListAdapter: PersonalExerciseListAdapter
    private val favExerciseViewModel: FavExerciseViewModel by viewModels {
        FavExerciseViewModelFactory((application as Application).favExerciseRepository)
    }

    private var groupList = mutableListOf<FavExercise>()
    private var rList = mutableListOf<FavExercise>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_plan)

        binding = ActivityPersonalPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nav.setOnItemSelectedListener { it ->
            when (it.itemId) {
                R.id.profile -> {
                    val intent = Intent(this@PersonalPlan, UserProfile::class.java)
                    startActivity(intent)
                    true
                }
                R.id.plan -> {
                    true
                }
                R.id.all -> {
                    val intent = Intent(this@PersonalPlan, AllExercises::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        favExerciseViewModel.allExercises.observe(owner = this) {
            exercises ->
            if(groupList.size == 0)
            {
                exercises.forEach {
                    if (it.added)
                        groupList.add(it)
                }
                exerciseListAdapter.updateData(groupList)
            }
        }

        val list = findViewById<RecyclerView>(R.id.recyclerview)

        exerciseListAdapter = PersonalExerciseListAdapter(
            groupList.toMutableList(),
            { deleteFavExercise(it) },
            { exerciseDetail(it) }
        )
        list.adapter = exerciseListAdapter
        list.layoutManager = LinearLayoutManager(this)
    }

    fun deleteFavExercise(exercise: FavExercise) {
        groupList = mutableListOf<FavExercise>()
        favExerciseViewModel.removeExercises(exercise.ename)

        Toast.makeText(
            applicationContext,
            "Exercise has been removed from your plan",
            Toast.LENGTH_LONG
        ).show()
    }

    fun exerciseDetail(exercise: FavExercise) {

        val intent = Intent(this@PersonalPlan, ExerciseDetail::class.java)
        val exercise2 = Exercise(exercise.ename, exercise.description, exercise.set, exercise.filter, exercise.group, exercise.added)
        intent.putExtra("Exercise", exercise2)
        startActivity(intent)
    }
}