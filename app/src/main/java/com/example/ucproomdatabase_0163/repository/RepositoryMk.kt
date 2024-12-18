package com.example.ucproomdatabase_0163.repository

import com.example.ucproomdatabase_0163.data.dao.matakuliahDao
import kotlinx.coroutines.flow.Flow

interface RepositoryMk {
    suspend fun insertMatakuliah(matakuliahDao: matakuliahDao)

    fun getAllMatakuliah(): Flow<List<matakuliahDao>>

    fun getMatakuliah(nidn :String) : Flow<matakuliahDao>

    suspend fun deleteMatakuliah(matakuliahDao: matakuliahDao)

    suspend fun updateMatakuliah(matakuliahDao: matakuliahDao)
}