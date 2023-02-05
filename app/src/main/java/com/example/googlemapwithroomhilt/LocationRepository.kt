package com.example.googlemapwithroomhilt

import androidx.lifecycle.LiveData
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class LocationRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {


    suspend fun insert(locationEntity: LocationEntity) = remoteDataSource.insert(locationEntity)

     fun getAllLocation() : LiveData<List<LocationEntity>>  = remoteDataSource.getAllLocation()

}