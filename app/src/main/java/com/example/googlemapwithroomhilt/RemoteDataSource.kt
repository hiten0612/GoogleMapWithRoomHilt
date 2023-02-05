package com.example.googlemapwithroomhilt

import androidx.lifecycle.LiveData
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val dao: LocationDao) {

    suspend fun insert(locationEntity: LocationEntity) = dao.insert(locationEntity)

    fun getAllLocation() : LiveData<List<LocationEntity>> = dao.getAllLocation()
}