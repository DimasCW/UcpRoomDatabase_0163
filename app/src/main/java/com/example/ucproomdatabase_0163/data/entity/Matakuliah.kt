package com.example.ucproomdatabase_0163.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matakuliah")
data class Matakuliah(
    @PrimaryKey
    val kode : String,
    val nama : String,
    val sks : String,
    val semester : String,
    val jenis : String,
    val dosenPengampu : String
)
