package com.example.testapplication.devicelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplication.db.Device
import com.example.testapplication.di.DeviceActivityScope
import com.example.testapplication.network.Repository
import com.example.testapplication.network.Result
import com.example.testapplication.response.DeviceResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@DeviceActivityScope
class DeviceViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _deviceStateFlow = MutableStateFlow<Result<List<Device>>>(Result.Loading)
    val deviceStateFlow: StateFlow<Result<List<Device>>> = _deviceStateFlow

    fun loadDevices() {
        viewModelScope.launch {
            repository.getAll().collect { entities ->
                if (entities.isEmpty()) {
                    loadRemote()
                } else {
                    _deviceStateFlow.emit(Result.Success(entities))
                }
            }
        }
    }

    private suspend fun loadRemote() {
        when (val response = repository.loadDevices()) {
            is Result.Success -> {
                val loadDevices = response.data.Devices.sortedBy {
                    it.pkDevice
                }.mapIndexed { index, device ->
                    transformDeviceResultToDevice("Home Number ${index + 1}", device)
                }

                repository.insertAll(*loadDevices.toTypedArray())
            }

            is Result.Failure -> _deviceStateFlow.emit(Result.Failure(response.apiError))
            is Result.Exception -> _deviceStateFlow.emit(Result.Exception(response.exception))
            is Result.Loading -> _deviceStateFlow.emit(Result.Loading)
        }
    }

    fun deleteDevice(device: Device) {
        viewModelScope.launch {
            repository.deleteDevice(device)
        }
    }

    fun updateDevice(device: Device) {
        viewModelScope.launch {
            repository.updateDevice(device)
        }
    }

    fun resetDevices() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

    private fun transformDeviceResultToDevice(title: String, deviceRes: DeviceResult): Device {
        return Device(
            firmware = deviceRes.firmware,
            internalIP = deviceRes.internalIP,
            lastAliveReported = deviceRes.lastAliveReported,
            macAddress = deviceRes.macAddress,
            pkAccount = deviceRes.pkAccount,
            pkDevice = deviceRes.pkDevice,
            pkDeviceSubType = deviceRes.pkDeviceSubType,
            pkDeviceType = deviceRes.pkDeviceType,
            platform = deviceRes.platform,
            serverAccount = deviceRes.serverAccount,
            serverDevice = deviceRes.serverDevice,
            serverEvent = deviceRes.serverEvent,
            title = title
        )
    }
}
