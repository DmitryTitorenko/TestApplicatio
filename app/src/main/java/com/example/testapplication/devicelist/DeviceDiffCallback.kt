package com.example.testapplication.devicelist

import androidx.recyclerview.widget.DiffUtil
import com.example.testapplication.db.Device

class DeviceDiffCallback(private val oldList: List<Device>, private val newList: List<Device>) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
