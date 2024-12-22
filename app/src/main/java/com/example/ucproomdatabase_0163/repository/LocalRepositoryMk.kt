package com.example.ucproomdatabase_0163.repository

import com.example.ucproomdatabase_0163.data.dao.MatakuliahDao
import com.example.ucproomdatabase_0163.data.entity.Matakuliah
import kotlinx.coroutines.flow.Flow


class LocalRepository(
    private val matakuliahDao: MatakuliahDao
) : Repository {
    override suspend fun insertMatakuliah(mataKuliah: Matakuliah) {
        matakuliahDao.insertMatakuliah(mataKuliah)

    }

    override fun getAllMatakuliah(): Flow<List<Matakuliah>> {
        return matakuliahDao.getAllMatakuliah()
    }

    override fun getMatakuliah(kode: String): Flow<Matakuliah> {
        return matakuliahDao.getMatakuliah(kode)
    }

    override suspend fun deleteMatakuliah(mataKuliah: Matakuliah) {
        matakuliahDao.deleteMatakuliah(mataKuliah)
    }

    override suspend fun updateMatakuliah(mataKuliah: Matakuliah) {
        matakuliahDao.updateMatakuliah(mataKuliah)
    }
}