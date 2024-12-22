package com.example.ucproomdatabase_0163.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucproomdatabase_0163.data.entity.Matakuliah
import kotlinx.coroutines.flow.Flow

@Dao
interface MatakuliahDao {
    @Insert
    suspend fun insertMatakuliah(mataKuliah: Matakuliah)

    //getAll matakuliah
    @Query("SELECT * FROM matakuliah ORDER BY nama ASC")
    fun getAllMatakuliah() : Flow<List<Matakuliah>>

    //get matakuliah
    @Query("SELECT * FROM matakuliah WHERE kode = :kode")
    fun getMatakuliah(kode: String) : Flow<Matakuliah>

    //delete
    @Delete
    suspend fun deleteMatakuliah(matakuliahDao: Matakuliah)

    //update
    @Update
    suspend fun updateMatakuliah(matakuliahDao: Matakuliah)
}

//Semua eksekusi diatas akan dikirim ke package repository