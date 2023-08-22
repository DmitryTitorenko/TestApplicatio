package com.example.testapplication.devicedescription

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testapplication.App
import com.example.testapplication.databinding.FragmentDeviceDescriptionBinding
import com.example.testapplication.db.Device
import com.example.testapplication.devicelist.DeviceViewModel
import com.example.testapplication.utils.parcelable
import javax.inject.Inject

class DeviceDescriptionFragment : Fragment() {
    private var _binding: FragmentDeviceDescriptionBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: DeviceViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
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
            tvDeviceSN.text = "SN: ${device.pkDevice}"
        }
    }
}
