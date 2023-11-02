package com.example.android.roomwordssample

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class FavExerciseRepository(private val favExerciseDao: FavExerciseDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allExercises: Flow<List<FavExercise>> = favExerciseDao.getAllExercise()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(favExercise: FavExercise) {
        favExerciseDao.insertExercise(favExercise)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAnExercise(name: String) {
        favExerciseDao.deleteAnExercise(name)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateExercises(name: String) {
        favExerciseDao.updateExercises(name)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun removeExercises(name: String) {
        favExerciseDao.removeExercises(name)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAll() {
        favExerciseDao.deleteAllExercise()
    }




}