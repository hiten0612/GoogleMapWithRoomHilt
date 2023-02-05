package com.example.googlemapwithroomhilt

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@dagger.Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides

    fun provideDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(app, LocationDatabase::class.java, "map.db").build()


    @Singleton
    @Provides
    fun provideYourDao(db: LocationDatabase) = db.locationDao()
}