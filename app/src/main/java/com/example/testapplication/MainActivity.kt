package com.example.testapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testapplication.databinding.ActivityMainBinding
import com.example.testapplication.devicelist.DeviceComponent
import com.example.testapplication.devicelist.DeviceViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    lateinit var deviceComponent: DeviceComponent

    @Inject
    lateinit var viewModel: DeviceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        deviceComponent = (applicationContext as App).appComponent.deviceListComponent().create()
        deviceComponent.inject(this)

        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initListener()
        viewModel.loadDevices()
    }

    private fun initListener() {
        binding.btnReset.setOnClickListener {
            viewModel.resetDevices()
        }
    }
}
