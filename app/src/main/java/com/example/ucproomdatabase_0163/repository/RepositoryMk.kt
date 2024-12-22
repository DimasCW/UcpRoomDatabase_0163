package com.example.ucproomdatabase_0163.repository

import com.example.ucproomdatabase_0163.data.entity.Matakuliah
import kotlinx.coroutines.flow.Flow

interface RepositoryMk {
    suspend fun insertMatakuliah(matakuliah: Matakuliah)

    fun getAllMatakuliah(): Flow<List<Matakuliah>>

    fun getMatakuliah(kode :String) : Flow<Matakuliah>

    suspend fun deleteMatakuliah(matakuliah: Matakuliah)

    suspend fun updateMatakuliah(matakuliah: Matakuliah)
}