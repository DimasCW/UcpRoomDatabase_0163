package com.example.ucproomdatabase_0163.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucproomdatabase_0163.data.dao.dosenDao
import com.example.ucproomdatabase_0163.data.entity.Dosen

@Database(entities = [Dosen::class], version = 1, exportSchema = false)
abstract class KrsDatabase : RoomDatabase() {

    //mendefinisikan fungsi untuk mengakses data mahasiswa
    abstract fun dosenDao() : dosenDao

    companion object{
        @Volatile //emmastikan bahwa nilai variable
        private var Instance : KrsDatabase ? = null

        fun getDatabase(context: Context) : KrsDatabase{
            return (Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    KrsDatabase::class.java,
                    "KrsDatabase"
                )
                    .build().also { Instance = it }
            })
        }
    }
}