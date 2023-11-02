package com.example.android.roomwordssample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class GroupExercises : AppCompatActivity() {

    private val favExerciseViewModel: FavExerciseViewModel by viewModels {
        FavExerciseViewModelFactory((application as Application).favExerciseRepository)
    }

    private var exerciseList = mutableListOf<FavExercise>()
    private var groupList = mutableListOf<FavExercise>()


    private lateinit var groupListAdapter : ExerciseListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_exercises)


        val group = intent.getStringExtra("Group")


        favExerciseViewModel.allExercises.observe(owner = this) {
            exercises ->
            if(groupList.size == 0) {
                exercises.forEach {
                    if (it.group == group)
                        groupList.add(it)
                }
            }
            groupListAdapter.updateData(groupList)
        }

        val list = findViewById<RecyclerView>(R.id.recyclerview)

        groupListAdapter = ExerciseListAdapter(groupList.toMutableList()) {addExerciseToPlan(it)}
        list.adapter = groupListAdapter
        list.layoutManager = LinearLayoutManager(this)
    }

    fun addExerciseToPlan(exercise: FavExercise) {
        groupList.forEach {
            if (it.ename == exercise.ename)
                it.added = true
        }
        groupListAdapter.updateData(groupList)
        favExerciseViewModel.updateExercises(exercise.ename)
        Toast.makeText(
            applicationContext,
            "Exercise has been added to your plan",
            Toast.LENGTH_LONG
        ).show()
    }

}