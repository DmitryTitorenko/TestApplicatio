package com.example.testapplication.di

import com.example.testapplication.devicelist.DeviceComponent
import dagger.Module

@Module(
    subcomponents = [DeviceComponent::class]
)
class AppSubcomponents
