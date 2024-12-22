package com.example.ucproomdatabase_0163.repository

import com.example.ucproomdatabase_0163.data.entity.Dosen
import com.example.ucproomdatabase_0163.data.entity.Matakuliah
import kotlinx.coroutines.flow.Flow

interface RepositoryDsn {
    suspend fun insertDosen(dosen: Dosen)

    fun getAllDosen(): Flow<List<Dosen>>

    fun getDosen(nidn :String) : Flow<Dosen>
}