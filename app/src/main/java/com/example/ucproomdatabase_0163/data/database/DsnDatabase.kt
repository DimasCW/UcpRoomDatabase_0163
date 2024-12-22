package com.example.ucproomdatabase_0163.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucproomdatabase_0163.data.dao.DosenDao
import com.example.ucproomdatabase_0163.data.entity.Dosen

@Database(entities = [Dosen::class], version = 1, exportSchema = false)
abstract class DsnDatabase : RoomDatabase() {

    //mendefinisikan fungsi untuk mengakses data mahasiswa
    abstract fun dosenDao() : DosenDao //ini berasal dari file dosenDao

    companion object{
        @Volatile //memastikan bahwa nilai variable
        private var Instance : DsnDatabase ? = null

        fun getDatabase(context: Context) : DsnDatabase{
            return (Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    DsnDatabase::class.java,
                    "DsnDatabase"
                )
                    .build().also { Instance = it }
            })
        }
    }
}