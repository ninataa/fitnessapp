package com.example.android.roomwordssample

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavExerciseDao {

    // The flow always holds/caches latest version of data. Notifies its observers when the
    // data has changed.
    @Query("SELECT * FROM exercise_table")
    fun getAllExercise(): Flow<List<FavExercise>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertExercise(favExercise: FavExercise)

    @Query("DELETE FROM exercise_table")
    suspend fun deleteAllExercise()

    @Query("DELETE FROM exercise_table WHERE ename = :name")
    suspend fun deleteAnExercise(name : String)

    @Query("UPDATE exercise_table SET added = 1 WHERE ename = :name")
    suspend fun updateExercises(name: String)

    @Query("UPDATE exercise_table SET added = 0 WHERE ename = :name")
    suspend fun removeExercises(name: String)
}