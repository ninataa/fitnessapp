package com.example.android.roomwordssample

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class PersonalExerciseListAdapter(private val data: MutableList<FavExercise>,
                                   private val listener: (FavExercise) -> Unit,
                                  private val listener2: (FavExercise) -> Unit): RecyclerView.Adapter<PersonalExerciseListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.exercise_layout, parent, false) as View
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    inner class ViewHolder(val v: View): RecyclerView.ViewHolder(v) {

        val name = v.findViewById<TextView>(R.id.ename)
        val set = v.findViewById<TextView>(R.id.set)
        val button = v.findViewById<Button>(R.id.add)
        val exercise = v.findViewById<LinearLayout>(R.id.exercise)
        @SuppressLint("ResourceAsColor")
        fun bind(item: FavExercise) {
            name.text = item.ename
            set.text = item.set
            button.text = "Remove"
            button.setOnClickListener {
                listener(item)
            }
            exercise.setOnClickListener{
                listener2(item)
            }
        }
    }

    fun updateData(newData: MutableList<FavExercise>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
}