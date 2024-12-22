package com.example.ucproomdatabase_0163

import android.app.Application
import com.example.ucproomdatabase_0163.dependenciesinjection.ContainerAppMk
import com.example.ucproomdatabase_0163.dependenciesinjection.InterfaceContainerAppMk

class MkApp : Application() {
    lateinit var containerAppMk: ContainerAppMk

    override fun onCreate() {
        super.onCreate()
        containerAppMk = ContainerAppMk(this)
    }
}