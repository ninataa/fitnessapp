package com.example.android.roomwordssample

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * View Model to keep a reference to the word repository and
 * an up-to-date list of all words.
 */

class FavExerciseViewModel(private val repository: FavExerciseRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allExercises: LiveData<List<FavExercise>> = repository.allExercises.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(favExercise: FavExercise) = viewModelScope.launch {
        repository.insert(favExercise)
    }

    fun deleteAnExercise(name: String) = viewModelScope.launch {
        repository.deleteAnExercise(name)
    }

    fun updateExercises(name: String) = viewModelScope.launch {
        repository.updateExercises(name)
    }

    fun removeExercises(name: String) = viewModelScope.launch {
        repository.removeExercises(name)
    }

    fun deleteAllExercise() = viewModelScope.launch {
        repository.deleteAll()
    }
}

class FavExerciseViewModelFactory(private val repository: FavExerciseRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavExerciseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavExerciseViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}