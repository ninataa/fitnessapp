package com.example.android.roomwordssample

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView

class ExerciseListAdapter(private val data: MutableList<FavExercise>,
                          private val listener: (FavExercise) -> Unit): RecyclerView.Adapter<ExerciseListAdapter.ViewHolder>() {

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
//        val desc = v.findViewById<TextView>(R.id.description)
        val button = v.findViewById<Button>(R.id.add)

        @SuppressLint("ResourceAsColor")
        fun bind(item: FavExercise) {
            name.text = item.ename
            set.text = item.set
            button.text = "Add"
//            desc.text = item.description
            button.isEnabled = !item.added
            if(!button.isEnabled)
            {
                val context: Context = v.context // Get the Context associated with the View
                button.setBackgroundColor(R.color.grey)
                button.setTextColor(ContextCompat.getColorStateList(context, R.color.grey))
            }
            button.setOnClickListener {
                button.isEnabled = false
                listener(item)
            }

        }
    }

    fun updateData(newData: MutableList<FavExercise>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
}