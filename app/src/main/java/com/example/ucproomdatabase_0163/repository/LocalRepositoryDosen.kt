package com.example.ucproomdatabase_0163.repository

import com.example.ucproomdatabase_0163.data.dao.dosenDao
import com.example.ucproomdatabase_0163.data.entity.Dosen
import kotlinx.coroutines.flow.Flow


class LocalRepositoryDosen(
    private val dosenDao: dosenDao
) : RepositoryDosen{
    override suspend fun insertDosen(dosen: Dosen) {
        dosenDao.insertDosen(dosen)
    }

    override fun getAllDosen() : Flow<List<Dosen>> {
        return dosenDao.getAllDosen()
    }

    override fun getDosen(nidn: String): Flow<Dosen>{
        return dosenDao.getDosen(nidn)
    }
}