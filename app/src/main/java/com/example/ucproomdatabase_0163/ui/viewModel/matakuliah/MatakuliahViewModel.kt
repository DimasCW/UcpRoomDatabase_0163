package com.example.ucproomdatabase_0163.ui.viewModel.matakuliah

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucproomdatabase_0163.data.entity.Matakuliah
import com.example.ucproomdatabase_0163.repository.Repository
import kotlinx.coroutines.launch

// Event adalah sebuah aksi (sesuatu yang dilakukan)
// State adalah hasil dari event (aksi)

data class MkUiState(
    val matakuliahEvent: MatakuliahEvent = MatakuliahEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null,
)

data class FormErrorState(
    val kode: String? = null,
    val nama: String? = null,
    val sks: String? = null,
    val semester: String? = null,
    val jenis: String? = null,
    val dosenPengampu: String? = null,
){
    fun isValid(): Boolean{
        return kode == null && sks == null &&
                semester == null && jenis == null && dosenPengampu == null
    }
}

// data class variabel yang menyimpan data input form
data class MatakuliahEvent(
    val kode: String = "",
    val nama: String = "",
    val sks: String = "",
    val semester: String = "",
    val jenis: String = "",
    val dosenPengampu: String = ""
)

// menyimpan input form ke dalam activity
fun MatakuliahEvent.toMatakuliahEntity(): Matakuliah = Matakuliah(
    kode = kode,
    nama = nama,
    sks = sks,
    semester = semester,
    jenis = jenis,
    dosenPengampu = dosenPengampu
)

class MatakuliahViewModel (private val  repository: Repository): ViewModel(){
    var uiState by mutableStateOf(MkUiState())

    // Memperbarui state berdasarkan input pengguna
    fun updateState(matakuliahEvent: MatakuliahEvent){
        uiState = uiState.copy(
            matakuliahEvent = matakuliahEvent,
        )
    }

    // Validasi data input pengguna
    private fun validateFields(): Boolean {
        val event = uiState.matakuliahEvent
        val errorState = FormErrorState(
            kode = if (event.kode.isNotEmpty()) null else "kode tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            sks = if (event.sks.isNotEmpty()) null else "Jenis Kelamin tidak boleh kosong",
            semester = if (event.semester.isNotEmpty()) null else "Alamat tidak boleh kosong",
            jenis = if (event.jenis.isNotEmpty()) null else "Kelas tidak boleh kosong",
            dosenPengampu = if (event.dosenPengampu.isNotEmpty()) null else "Angkatan tidak boleh kosong"
        )

        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    // Menyimpan data ke repository
    fun saveData(){
        val currentEvent = uiState.matakuliahEvent
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repository.insertMatakuliah(currentEvent.toMatakuliahEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        matakuliahEvent = MatakuliahEvent(), // Reset input form
                        isEntryValid = FormErrorState() // Reset error state
                    )
                } catch (e: Exception){
                    uiState = uiState.copy(
                        snackBarMessage = "Data gagal disimpan"
                    )
                }
            }
        } else {
            uiState = uiState.copy(
                snackBarMessage = "Input tidak valid. Periksa kembali data anda."
            )
        }
    }

    //Reset pesan snackBar setelah ditampilkan
    fun resetSnackBarMessage() {
        uiState = uiState.copy(snackBarMessage = null)
    }
}