package com.example.ucproomdatabase_0163.ui.viewModel.matakuliah

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucproomdatabase_0163.data.entity.Matakuliah
import com.example.ucproomdatabase_0163.repository.RepositoryMk
import com.example.ucproomdatabase_0163.ui.navigation.DestinasiDetailMk
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailMkViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMk: RepositoryMk,
): ViewModel(){
    private val _kode: String = checkNotNull(savedStateHandle[DestinasiDetailMk.KODE])

    val detailUiStateMk: StateFlow<DetailUiStateMk> = repositoryMk.getMatakuliah(_kode)
        .filterNotNull()
        .map {
            DetailUiStateMk(
                detailUiEvent = it.toDetailUiEvent(),
                isLoading = false,
            )
        }
        .onStart {
            emit(DetailUiStateMk(isLoading = true))
            delay(600)
        }
        .catch {
            emit(
                DetailUiStateMk(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan",
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailUiStateMk(
                isLoading = true
            ),
        )

    fun deleteMk(){
        detailUiStateMk.value.detailUiEvent.toMatakuliahEntity().let{
            viewModelScope.launch {
                repositoryMk.deleteMatakuliah(it)
            }
        }
    }
}

data class DetailUiStateMk(
    val detailUiEvent: MatakuliahEvent = MatakuliahEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
){
    val isUiEventEmpty:Boolean
        get() = detailUiEvent == MatakuliahEvent()

    val isUiEventnotEmpty: Boolean
        get() = detailUiEvent != MatakuliahEvent()
}

//Data class untuk menampung data yang akan ditampilkan di UI

//memindahkan data dari entity ke ui
fun Matakuliah.toDetailUiEvent() : MatakuliahEvent{
    return MatakuliahEvent(
        kode = kode,
        nama = nama,
        sks = sks,
        semester = semester,
        jenis = jenis,
        dosenPengampu = dosenPengampu
    )
}