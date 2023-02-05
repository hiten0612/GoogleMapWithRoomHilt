package com.example.googlemapwithroomhilt

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LocationViewModel @Inject constructor(
    private val repository: LocationRepository,
    application: Application
) :
    AndroidViewModel(application) {

    val getAllLocation: LiveData<List<LocationEntity>> = repository.getAllLocation()

    init {
        getAllLocation()
    }

    fun insert(locationEntity: LocationEntity) = viewModelScope.launch {
        repository.insert(locationEntity)
    }

    private fun getAllLocation(): LiveData<List<LocationEntity>> = repository.getAllLocation()
}
