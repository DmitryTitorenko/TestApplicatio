package com.example.testapplication.devicelist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.R
import com.example.testapplication.db.Device
import com.example.testapplication.utils.setIcon

class DeviceAdapter(
    initList: List<Device>,
    val deviceClick: (Device) -> Unit,
    val deviceDelete: (Device) -> Unit,
    val deviceEdit: (Device) -> Unit
) : RecyclerView.Adapter<DeviceAdapter.ViewHolder>() {

    private val deviceList = mutableListOf<Device>()

    init {
        deviceList.addAll(initList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.device, parent, false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            setIcon(ivDevice, deviceList[position].platform)
            tvDeviceTitle.text = setDeviceTitle(view.context, position)
            tvDeviceSN.text = setDeviceSN(view.context, position)
        }
    }

    override fun getItemCount() = deviceList.size

    fun swap(devices: List<Device>) {
        val diffCallback = DeviceDiffCallback(this.deviceList, devices)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.deviceList.clear()
        this.deviceList.addAll(devices)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val ivDevice: ImageView = view.findViewById(R.id.ivDevice)
        val tvDeviceTitle: TextView = view.findViewById(R.id.tvDeviceTitle)
        val tvDeviceSN: TextView = view.findViewById(R.id.tvDeviceSN)

        init {
            view.setOnClickListener {
                if (absoluteAdapterPosition != RecyclerView.NO_POSITION) {
                    deviceClick(deviceList[absoluteAdapterPosition])
                }
            }
        }
    }

//    private fun setIcon(imageView: ImageView, platform: String) {
//        when (platform) {
//            "Sercomm G450" -> {
//                imageView.setImageResource(R.drawable.vera_plus_big)
//            }
//
//            "Sercomm G550" -> {
//                imageView.setImageResource(R.drawable.vera_secure_big)
//            }
//
//            "MiCasaVerde VeraLite", "Sercomm NA900", "Sercomm NA301", "Sercomm NA930", "" -> {
//                imageView.setImageResource(R.drawable.vera_edge_big)
//            }
//        }
//    }

    private fun setDeviceTitle(context: Context, position: Int) =
        "${context.getString(R.string.home_number)} " + " ${deviceList[position].deviceIndex}"

    private fun setDeviceSN(context: Context, position: Int) =
        "${context.getString(R.string.sn)}:" + " ${deviceList[position].pkDevice}"
}
