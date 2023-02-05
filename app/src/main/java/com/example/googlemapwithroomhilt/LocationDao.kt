package com.example.googlemapwithroomhilt

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(locationEntity: LocationEntity)

    @Update
    fun update(locationEntity: LocationEntity)

    @Delete
    fun delete(locationEntity: LocationEntity)

    @Query("SELECT * FROM location_table")
    fun getAllLocation(): LiveData<List<LocationEntity>>
}