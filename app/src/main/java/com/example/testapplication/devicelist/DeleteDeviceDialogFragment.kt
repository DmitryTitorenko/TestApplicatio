package com.example.testapplication.devicelist

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.testapplication.R

class DeleteDeviceDialogFragment(val positiveClick: () -> Unit) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.delete_device).setPositiveButton(
                R.string.ok
            ) { _, _ ->
                positiveClick()
            }.setNegativeButton(
                R.string.cancel
            ) { _, _ ->
            }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
