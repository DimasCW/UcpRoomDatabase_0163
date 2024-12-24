package com.example.ucproomdatabase_0163.ui.viewModel.dosen

import androidx.lifecycle.SavedStateHandle
import com.example.ucproomdatabase_0163.repository.RepositoryDsn
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucproomdatabase_0163.data.entity.Dosen
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


class HomeDsnViewModel(
    private val repositoryDsn: RepositoryDsn
) : ViewModel(){

    val homeUiState : StateFlow<HomeUiState> = repositoryDsn.getAllDosen()
        .filterNotNull()
        .map {
            HomeUiState(
                listDsn = it.toList(),
                isLoading = false
            )
        }
        .onStart {
            emit(HomeUiState(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                HomeUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiState(
                isLoading = true,
            )
        )
}

data class HomeUiState(
    val listDsn: List<Dosen> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)





