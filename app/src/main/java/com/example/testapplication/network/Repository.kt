package com.example.testapplication.network

import com.example.testapplication.db.Device
import com.example.testapplication.db.DeviceDao
import com.example.testapplication.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val api: Api,
    private val dao: DeviceDao
) : BaseRepo() {

    suspend fun loadDevices() = withContext(ioDispatcher) {
        handleRequest(api.loadDevices())
    }

    fun getAll() = dao.getAll()

    suspend fun insertAll(vararg devices: Device) = withContext(ioDispatcher) {
        dao.insertAll(*devices)
    }

    suspend fun deleteDevice(device: Device) = withContext(ioDispatcher) {
        dao.deleteDevice(device)
    }

    suspend fun updateDevice(device: Device) = withContext(ioDispatcher) {
        dao.updateDevice(device)
    }

    suspend fun deleteAll() = withContext(ioDispatcher) {
        dao.deleteAll()
    }
}
