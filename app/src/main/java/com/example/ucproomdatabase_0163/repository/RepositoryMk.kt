package com.example.ucproomdatabase_0163.repository

import com.example.ucproomdatabase_0163.data.entity.Matakuliah
import kotlinx.coroutines.flow.Flow

interface RepositoryMk {
    suspend fun insertMatakuliah(mataKuliah: Matakuliah)

    fun getAllMatakuliah(): Flow<List<Matakuliah>>

    fun getMatakuliah(nidn :String) : Flow<Matakuliah>

    suspend fun deleteMatakuliah(mataKuliah: Matakuliah)

    suspend fun updateMatakuliah(mataKuliah: Matakuliah)
}