package com.example.ucproomdatabase_0163

import android.app.Application
import com.example.ucproomdatabase_0163.dependenciesinjection.ContainerApp
import com.example.ucproomdatabase_0163.dependenciesinjection.InterfaceContainerApp

class MkApp : Application() {
    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this)
    }
}
