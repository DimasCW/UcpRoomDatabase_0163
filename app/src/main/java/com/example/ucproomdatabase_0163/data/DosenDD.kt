package com.example.ucproomdatabase_0163.data

import android.content.Context
import com.example.ucproomdatabase_0163.MkApp
import com.example.ucproomdatabase_0163.data.database.DsnDatabase
import com.example.ucproomdatabase_0163.data.entity.Dosen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object DosenDD {
//    var option: List<Dosen> = listOf()
//    fun loadData(context: Context) {
//        val db = DsnDatabase.getDatabase(context)
//        val dosenDao = db.dosenDao()
//
//        // Gunakan coroutine untuk mendapatkan data
//        runBlocking {
//            option = dosenDao.getAllDosen().first() // Mengambil data pertama dari Flow
//        }
//    }
// StateFlow untuk data dosen
private val _option = MutableStateFlow<List<Dosen>>(emptyList())
    val option: StateFlow<List<Dosen>> = _option

    fun loadData(context: Context) {
        val db = DsnDatabase.getDatabase(context)
        val dosenDao = db.dosenDao()

        // Coroutine untuk memantau perubahan data secara realtime
        CoroutineScope(Dispatchers.IO).launch {
            dosenDao.getAllDosen().collectLatest { dosenList ->
                _option.value = dosenList // Perbarui StateFlow dengan data baru
            }
        }
    }
}