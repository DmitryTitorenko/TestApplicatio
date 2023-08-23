package com.example.testapplication.devicelist

import com.example.testapplication.MainActivity
import com.example.testapplication.di.DeviceActivityScope
import dagger.Subcomponent

@DeviceActivityScope
@Subcomponent
interface DeviceComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): DeviceComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: DeviceFragment)
}
