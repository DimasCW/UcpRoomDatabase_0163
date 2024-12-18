package com.example.ucproomdatabase_0163.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucproomdatabase_0163.data.entity.Dosen
import kotlinx.coroutines.flow.Flow


//Step 2
@Dao
interface dosenDao {
    @Insert
    suspend fun insertDosen(dosen: Dosen) /**memasukkan table Dosen pada package entity**/

    //-----
    //getAllMahasiswa
    @Query("SELECT * FROM dosen ORDER BY nama ASC")
    fun getAllMahasiwa() : Flow<List<Dosen>>


}
