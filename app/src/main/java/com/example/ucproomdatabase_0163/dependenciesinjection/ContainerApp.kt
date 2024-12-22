package com.example.ucproomdatabase_0163.dependenciesinjection

import android.content.Context
import com.example.ucproomdatabase_0163.data.database.MkDatabase
import com.example.ucproomdatabase_0163.repository.LocalRepositoryDosen
import com.example.ucproomdatabase_0163.repository.LocalRepositoryMk
import com.example.ucproomdatabase_0163.repository.RepositoryDosen
import com.example.ucproomdatabase_0163.repository.RepositoryMk

interface InterfaceContainerApp{
    val repositoryMk: RepositoryMk
    val  repositoryDosen : RepositoryDosen
}

class ContainerApp(private val context: Context): InterfaceContainerApp{
    override val repositoryDosen: RepositoryDosen by lazy {
        LocalRepositoryDosen(MkDatabase.getDatabase(context).dosenDao())
    }

    override val repositoryMk: RepositoryMk by lazy {
        LocalRepositoryMk(MkDatabase.getDatabase(context).matakuliahDao())
    }
}