package com.example.ucproomdatabase_0163.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucproomdatabase_0163.data.dao.MatakuliahDao
import com.example.ucproomdatabase_0163.data.entity.Matakuliah

@Database(entities = [Matakuliah::class], version = 1, exportSchema = false)
abstract class MkDatabase : RoomDatabase() {

    //mendefinisikan fungsi untuk mengakses data mahasiswa
    abstract fun matakuliahDao() : MatakuliahDao //ini berasal dari file matakuliahDao

    companion object{
        @Volatile //memastikan bahwa nilai variable
        private var Instance : MkDatabase ? = null

        fun getDatabase(context: Context) : MkDatabase{
            return (Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    MkDatabase::class.java,
                    "MkDatabase"
                )
                    .build().also { Instance = it }
            })
        }
    }
}