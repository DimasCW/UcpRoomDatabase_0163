package com.example.ucproomdatabase_0163.ui.viewModel.matakuliah

import com.example.ucproomdatabase_0163.data.entity.Matakuliah
import com.example.ucproomdatabase_0163.repository.RepositoryMk
import com.example.ucproomdatabase_0163.ui.navigation.DestinasiUpdateMk
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateMkViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMk: RepositoryMk
): ViewModel() {
    var updateUIState by mutableStateOf(MkUiState())
        private set

    private val _kode: String = checkNotNull(savedStateHandle[DestinasiUpdateMk.KODE])

    init {
        viewModelScope.launch {
            updateUIState = repositoryMk.getMatakuliah(_kode)
                .filterNotNull()
                .first()
                .toUIStateMk()
        }
    }

    fun updateState(matakuliahEvent: MatakuliahEvent) {
        updateUIState = updateUIState.copy(
            matakuliahEvent = matakuliahEvent,
        )
    }

    fun validateFields(): Boolean {
        val event = updateUIState.matakuliahEvent
        val errorState = FormErrorState(
            kode = if (event.kode.isNotEmpty()) null else "NIM tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            jenis = if (event.jenis.isNotEmpty()) null else " Jenis Kelamin tidak boleh kosong",
            sks = if (event.sks.isNotEmpty()) null else "Alamat tidak boleh kosong",
            semester = if (event.semester.isNotEmpty()) null else "Kelas Tidak boleh kosong",
            dosenPengampu = if (event.dosenPengampu.isNotEmpty()) null else "Angkatan tidak boleh kosong"
        )

        updateUIState = updateUIState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun updateData() {
        val currentEvent = updateUIState.matakuliahEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryMk.updateMatakuliah(currentEvent.toMatakuliahEntity())
                    updateUIState = updateUIState.copy(
                        snackBarMessage = "Data berhasil di update",
                        matakuliahEvent = MatakuliahEvent(),
                        isEntryValid = FormErrorState()
                    )
                    println("sanckBarMessage diatur: ${updateUIState.snackBarMessage}")
                } catch (e: Exception) {
                    updateUIState = updateUIState.copy(
                        snackBarMessage = "Data gagal di update"
                    )
                }
            }
        } else {
            updateUIState = updateUIState.copy(
                snackBarMessage = "data gagal diupdate"
            )
        }
    }

    fun resetSnackBarMessage() {
        updateUIState = updateUIState.copy(snackBarMessage = null)
    }
}

fun Matakuliah.toUIStateMk(): MkUiState = MkUiState(
    matakuliahEvent = this.toDetailUiEvent(),
)