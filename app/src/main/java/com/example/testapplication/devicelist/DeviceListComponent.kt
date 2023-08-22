package com.example.testapplication.devicelist

import com.example.testapplication.di.DeviceFragmentScope
import dagger.Subcomponent

@DeviceFragmentScope
@Subcomponent
interface DeviceListComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): DeviceListComponent
    }

    fun inject(fragment: DeviceFragment)
}
