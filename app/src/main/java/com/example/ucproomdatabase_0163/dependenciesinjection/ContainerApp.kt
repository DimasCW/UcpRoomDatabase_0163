package com.example.ucproomdatabase_0163.dependenciesinjection

import android.content.Context
import com.example.ucproomdatabase_0163.data.database.MkDatabase
import com.example.ucproomdatabase_0163.repository.LocalRepository
import com.example.ucproomdatabase_0163.repository.Repository

interface InterfaceContainerApp{
    val repository: Repository
}

class ContainerApp(private val context: Context): InterfaceContainerApp{

    override val repository: Repository by lazy {
        LocalRepository(MkDatabase.getDatabase(context).matakuliahDao())
    }
}