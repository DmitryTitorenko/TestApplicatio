package com.example.testapplication.response

import com.google.gson.annotations.SerializedName

data class DeviceResult(

    @field:SerializedName("Firmware")
    val firmware: String,

    @field:SerializedName("InternalIP")
    val internalIP: String,

    @field:SerializedName("LastAliveReported")
    val lastAliveReported: String,

    @field:SerializedName("MacAddress")
    val macAddress: String,

    @field:SerializedName("PK_Account")
    val pkAccount: Int,

    @field:SerializedName("PK_Device")
    val pkDevice: Int,

    @field:SerializedName("PK_DeviceSubType")
    val pkDeviceSubType: Int,

    @field:SerializedName("PK_DeviceType")
    val pkDeviceType: Int,

    @field:SerializedName("Platform")
    val platform: String,

    @field:SerializedName("Server_Account")
    val serverAccount: String,

    @field:SerializedName("Server_Device")
    val serverDevice: String,

    @field:SerializedName("Server_Event")
    val serverEvent: String
)