package com.chrisdang.RoomMates

import androidx.lifecycle.*
import androidx.room.Room
import kotlinx.coroutines.launch

class RoomMateViewModel(private val repository: RoomMateRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allWords: LiveData<List<RoomMates>> = repository.allRoomMates.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(roomMates: RoomMates) = viewModelScope.launch {
        repository.insert(roomMates)
    }
}

class RoomMateViewModelFactory(private val repository: RoomMateRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoomMateViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RoomMateViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}