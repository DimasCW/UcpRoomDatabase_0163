package com.example.ucproomdatabase_0163.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucproomdatabase_0163.data.entity.mataKuliah
import kotlinx.coroutines.flow.Flow

@Dao
interface matakuliahDao {
    @Insert
    suspend fun insertMatakuliah(matakuliahDao: matakuliahDao)

    //getAll matakuliah
    @Query("SELECT * FROM matakuliah ORDER BY nama ASC")
    fun getAllMatakuliah() : Flow<List<matakuliahDao>>

    //get matakuliah
    @Query("SELECT * FROM matakuliah WHERE kode = :kode")
    fun getMatakuliah(kode: String) : Flow<mataKuliah>

    //delete
    @Delete
    suspend fun deleteMatakuliah(matakuliahDao: matakuliahDao)

    //update
    suspend fun updateMatakuliah(matakuliahDao: matakuliahDao)
}

//Semua eksekusi diatas akan dikirim ke package repository