package com.example.testapplication.devicedescription

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentDeviceDescriptionBinding
import com.example.testapplication.db.Device
import com.example.testapplication.devicelist.setIcon
import com.example.testapplication.utils.parcelable

class DeviceDescriptionFragment : Fragment() {
    private var _binding: FragmentDeviceDescriptionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeviceDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDeviceDescription()
    }

    private fun setDeviceDescription() {
        val device: Device = arguments?.parcelable("device") ?: return

        binding.apply {
            setIcon(ivDevice, device.platform)
            tvDeviceTitle.text = "${getString(R.string.home_number)} ${device.deviceIndex}"

            tvDeviceSN.text = "${getString(R.string.sn)}: ${device.pkDevice}"
            tvDeviceMAC.text = "${getString(R.string.mac_address)}: ${device.macAddress}"
            tvDeviceFirmware.text = "${getString(R.string.firmware)}: ${device.firmware}"
            tvDeviceModel.text = "${getString(R.string.model)}: ${setModel(device.platform)}"
        }
    }

    private fun setModel(platform: String): String {
        return when (platform) {
            "Sercomm G450" -> {
                getString(R.string.vera_plus)
            }

            "Sercomm G550" -> {
                getString(R.string.vera_secure)
            }

            "MiCasaVerde VeraLite", "Sercomm NA900", "Sercomm NA301", "Sercomm NA930", "" -> {
                getString(R.string.vera_edge)
            }

            else -> {
                ""
            }
        }
    }
}
