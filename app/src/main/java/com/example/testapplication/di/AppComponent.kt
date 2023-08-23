package com.example.testapplication.di

import android.content.Context
import com.example.testapplication.db.RoomModule
import com.example.testapplication.devicelist.DeviceComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CoroutinesModule::class, AppSubcomponents::class, RetrofitModule::class, RoomModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun deviceComponent(): DeviceComponent.Factory
}
