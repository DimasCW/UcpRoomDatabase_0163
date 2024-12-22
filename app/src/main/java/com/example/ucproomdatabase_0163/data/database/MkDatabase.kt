package com.example.ucproomdatabase_0163.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucproomdatabase_0163.data.dao.dosenDao
import com.example.ucproomdatabase_0163.data.dao.MatakuliahDao
import com.example.ucproomdatabase_0163.data.entity.Dosen

@Database(entities = [Dosen::class], version = 1, exportSchema = false)
abstract class MkDatabase : RoomDatabase() {

    //mendefinisikan fungsi untuk mengakses data mahasiswa
    abstract fun dosenDao() : dosenDao //ini berasal dari file dosenDao
    abstract fun matakuliahDao() : MatakuliahDao //ini berasal dari file matakuliahDao

    companion object{
        @Volatile //memastikan bahwa nilai variable
        private var Instance : MkDatabase ? = null

        fun getDatabase(context: Context) : MkDatabase{
            return (Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    MkDatabase::class.java,
                    "KrsDatabase"
                )
                    .build().also { Instance = it }
            })
        }
    }
}