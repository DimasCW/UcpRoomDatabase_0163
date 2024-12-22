package com.example.ucproomdatabase_0163.dependenciesinjection

import android.content.Context
import com.example.ucproomdatabase_0163.data.database.DsnDatabase
import com.example.ucproomdatabase_0163.data.database.MkDatabase
import com.example.ucproomdatabase_0163.repository.LocalRepositoryDsn
import com.example.ucproomdatabase_0163.repository.LocalRepositoryMk
import com.example.ucproomdatabase_0163.repository.RepositoryDsn
import com.example.ucproomdatabase_0163.repository.RepositoryMk

interface InterfaceContainerApp{
    val repositoryMk: RepositoryMk
    val repositoryDsn: RepositoryDsn
}

class ContainerApp(private val context: Context): InterfaceContainerApp{

    override val repositoryMk: RepositoryMk by lazy {
        LocalRepositoryMk(MkDatabase.getDatabase(context).matakuliahDao())
    }
    override val repositoryDsn: RepositoryDsn by lazy {
        LocalRepositoryDsn(DsnDatabase.getDatabase(context).dosenDao())
    }
}