package com.example.ucproomdatabase_0163.data

import android.content.Context
import com.example.ucproomdatabase_0163.MkApp
import com.example.ucproomdatabase_0163.data.database.DsnDatabase
import com.example.ucproomdatabase_0163.data.entity.Dosen
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object DosenDD {
    var option: List<Dosen> = listOf()
    fun loadData(context: Context) {
        val db = DsnDatabase.getDatabase(context)
        val dosenDao = db.dosenDao()

        // Gunakan coroutine untuk mendapatkan data
        runBlocking {
            option = dosenDao.getAllDosen().first() // Mengambil data pertama dari Flow
        }
    }
}