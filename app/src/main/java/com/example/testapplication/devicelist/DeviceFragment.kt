package com.example.testapplication.devicelist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapplication.App
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentDevicesBinding
import com.example.testapplication.db.Device
import com.example.testapplication.network.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class DeviceFragment : Fragment() {
    private var _binding: FragmentDevicesBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: DeviceViewModel

    private lateinit var deviceAdapter: DeviceAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as App).appComponent.deviceListComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDevicesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initListener()
        initLoad()
    }

    private fun initAdapter() {
        binding.rvDevices.setHasFixedSize(true)
        binding.rvDevices.layoutManager = LinearLayoutManager(context)
        binding.rvDevices.addItemDecoration(
            DividerItemDecoration(
                requireContext(), LinearLayoutManager.VERTICAL
            )
        )
        deviceAdapter = DeviceAdapter(listOf(), { deviceClick ->
            val bundle = Bundle().apply {
                putParcelable("device", deviceClick)
            }

            findNavController().navigate(
                R.id.action_deviceFragment_to_deviceDescriptionFragment, bundle
            )

        }, { deleteDevice ->
            showDialog(deleteDevice)
        })
        binding.rvDevices.adapter = deviceAdapter
    }

    private fun initListener() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.deviceStateFlow.collect { response ->
                    when (response) {
                        is Result.Success -> {
                            deviceAdapter.swap(response.data)
                        }

                        is Result.Failure -> Log.d("RESULT", "Failure: ${response.apiError}")
                        is Result.Exception -> Log.d("RESULT", "Exception: ${response.exception}")
                        is Result.Loading -> Log.d("RESULT", "Loading")
                    }
                }
            }
        }
    }

    private fun initLoad() {
        viewModel.loadDevices()
    }

    private fun showDialog(device: Device) {
        val dialogFragment = DeleteDeviceDialogFragment {
            viewModel.deleteDevice(device)
        }
        dialogFragment.show(childFragmentManager, "dialog")
    }
}
