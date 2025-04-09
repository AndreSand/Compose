package com.android.example.compose_training

import android.app.Application
import com.android.example.compose_training.data.AppContainer
import com.android.example.compose_training.data.AppDataContainer

class InventoryApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}