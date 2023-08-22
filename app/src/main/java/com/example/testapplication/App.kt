package com.example.testapplication

import android.app.Application
import com.example.testapplication.di.AppComponent
import com.example.testapplication.di.DaggerAppComponent

class App : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}
