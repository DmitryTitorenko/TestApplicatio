package com.example.testapplication.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DeviceDao {
    @Query("SELECT * FROM device")
    fun getAll(): Flow<List<Device>>

    @Insert
    suspend fun insertAll(vararg devices: Device)

    @Delete
    suspend fun deleteDevice(device: Device)

    @Update
    suspend fun updateDevice(device: Device)

    @Query("DELETE FROM device")
    suspend fun deleteAll()
}
