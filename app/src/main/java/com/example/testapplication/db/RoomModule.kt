package com.example.testapplication.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideRoom(
        context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "deviceDB"
        ).build()
    }

    @Provides
    fun provideDeviceDao(
        appDatabase: AppDatabase
    ): DeviceDao {
        return appDatabase.deviceDao()
    }
}
