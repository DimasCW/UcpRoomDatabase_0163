package com.example.ucproomdatabase_0163.dependenciesinjection

import android.content.Context
import com.example.ucproomdatabase_0163.data.database.MkDatabase
import com.example.ucproomdatabase_0163.repository.LocalRepositoryMk
import com.example.ucproomdatabase_0163.repository.RepositoryMk

interface InterfaceContainerApp{
    val repositoryMk: RepositoryMk
}

class ContainerApp(private val context: Context): InterfaceContainerApp{

    override val repositoryMk: RepositoryMk by lazy {
        LocalRepositoryMk(MkDatabase.getDatabase(context).matakuliahDao())
    }
}