package com.example.android.roomwordssample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.roomwordssample.databinding.ActivityGroupExercisesBinding
import com.example.android.roomwordssample.databinding.ActivityUserProfileBinding


class GroupExercises : AppCompatActivity() {
    private  lateinit var binding: ActivityGroupExercisesBinding

    private val favExerciseViewModel: FavExerciseViewModel by viewModels {
        FavExerciseViewModelFactory((application as Application).favExerciseRepository)
    }

    private var exerciseList = mutableListOf<FavExercise>()
    private var groupList = mutableListOf<FavExercise>()


    private lateinit var groupListAdapter : ExerciseListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_exercises)
        binding = ActivityGroupExercisesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nav.setOnItemSelectedListener {it ->
            when(it.itemId){
                R.id.profile -> {
                    val intent = Intent(this@GroupExercises, UserProfile::class.java)
                    startActivity(intent)
                    true
                }
                R.id.plan -> {
                    val intent = Intent(this@GroupExercises, PersonalPlan::class.java)
                    startActivity(intent)
                    true}
                R.id.all -> {
                    val intent = Intent(this@GroupExercises, AllExercises::class.java)
                    startActivity(intent)
                    true}
                else -> false
            }
        }



        val group = intent.getStringExtra("Group")

        val title = findViewById<TextView>(R.id.musclegroup)
        title.text = group + " Exercise"


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
    }

}