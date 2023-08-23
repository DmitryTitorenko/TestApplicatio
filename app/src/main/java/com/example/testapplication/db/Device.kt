package com.example.testapplication.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "device")
data class Device(

    val firmware: String,

    @ColumnInfo("internal_ip")
    val internalIP: String,

    @ColumnInfo("last_alive_reported")
    val lastAliveReported: String,

    @ColumnInfo("mac_address")
    val macAddress: String,

    @ColumnInfo("pk_account")
    val pkAccount: Int,

    @PrimaryKey
    @ColumnInfo("pk_device")
    val pkDevice: Int,

    @ColumnInfo("pk_device_sub_type")
    val pkDeviceSubType: Int,

    @ColumnInfo("pk_device_type")
    val pkDeviceType: Int,

    val platform: String,

    @ColumnInfo("server_account")
    val serverAccount: String,

    @ColumnInfo("server_device")
    val serverDevice: String,

    @ColumnInfo("server_event")
    val serverEvent: String,

    @ColumnInfo("title")
    val title: String
) : Parcelable
