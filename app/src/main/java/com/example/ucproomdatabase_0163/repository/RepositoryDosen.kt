package com.example.ucproomdatabase_0163.repository

import com.example.ucproomdatabase_0163.data.dao.dosenDao
import com.example.ucproomdatabase_0163.data.entity.Dosen
import kotlinx.coroutines.flow.Flow


interface RepositoryDosen {
    suspend fun insertDosen(dosen: Dosen)

    fun getAllDosen(): Flow<List<Dosen>>

    fun getDosen(nidn: String) : Flow<Dosen>
}