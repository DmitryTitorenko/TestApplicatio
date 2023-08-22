package com.example.testapplication.di

import com.example.testapplication.devicelist.DeviceListComponent
import dagger.Module

@Module(
    subcomponents = [DeviceListComponent::class]
)
class AppSubcomponents
