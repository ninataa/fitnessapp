package com.example.android.roomwordssample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_table")
data class FavExercise(@PrimaryKey @ColumnInfo(name = "ename") val ename: String
                , @ColumnInfo(name = "description") val description: String
                , @ColumnInfo(name = "set") val set: String
                , @ColumnInfo(name = "filter") val filter: String
                , @ColumnInfo(name = "group") val group: String
                , @ColumnInfo(name = "added") var added: Boolean)