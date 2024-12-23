package com.example.ucproomdatabase_0163.ui.viewModel.dosen

import com.example.ucproomdatabase_0163.ui.viewModel.matakuliah.MatakuliahEvent
import com.example.ucproomdatabase_0163.ui.viewModel.matakuliah.toMatakuliahEntity
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucproomdatabase_0163.data.entity.Dosen
import com.example.ucproomdatabase_0163.data.entity.Matakuliah
import com.example.ucproomdatabase_0163.repository.RepositoryDsn
import com.example.ucproomdatabase_0163.repository.RepositoryMk
import com.example.ucproomdatabase_0163.ui.navigation.DestinasiDetail
import com.example.ucproomdatabase_0163.ui.navigation.DestinasiDetailDsn
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailDsnViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryDsn: RepositoryDsn,
): ViewModel(){
    private val _nidn: String = checkNotNull(savedStateHandle[DestinasiDetailDsn.NIDN])

    val detailUiState: StateFlow<DetailUiState> = repositoryDsn.getDosen(_nidn)
        .filterNotNull()
        .map {
            DetailUiState(
                detailUiEvent = it.toDetailUiEvent(),
                isLoading = false,
            )
        }
        .onStart {
            emit(DetailUiState(isLoading = true))
            delay(600)
        }
        .catch {
            emit(
                DetailUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan",
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailUiState(
                isLoading = true
            ),
        )
    fun deleteDsn(){
        detailUiState.value.detailUiEvent.toDosenEntity().let{
            viewModelScope.launch {
                repositoryDsn.deleteDosen(it)
            }
        }
    }
}

data class DetailUiState(
    val detailUiEvent: DosenEvent = DosenEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
){
    val isUiEventEmpty:Boolean
        get() = detailUiEvent == DosenEvent()

    val isUiEventnotEmpty: Boolean
        get() = detailUiEvent != DosenEvent()
}

//Data class untuk menampung data yang akan ditampilkan di UI

//memindahkan data dari entity ke ui
fun Dosen.toDetailUiEvent() : DosenEvent {
    return DosenEvent(
        nidn = nidn,
        nama = nama,
        jenisKelamin = jenisKelamin,
    )
}