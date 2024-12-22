package com.example.ucproomdatabase_0163.ui.viewModel.matakuliah

import com.example.ucproomdatabase_0163.data.entity.Matakuliah
import com.example.ucproomdatabase_0163.repository.RepositoryMk
import androidx.lifecycle.ViewModel
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class HomeMkViewModel(
    private val repositoryMk: RepositoryMk
) : ViewModel(){
    val homeUiState : StateFlow<HomeUiStateMk> = repositoryMk.getAllMatakuliah()
        .filterNotNull()
        .map {
            HomeUiStateMk(
                listMk = it.toList(),
                isLoading = false
            )
        }
        .onStart {
            emit(HomeUiStateMk(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                HomeUiStateMk(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiStateMk(
                isLoading = true,
            )
        )
}

data class HomeUiStateMk(
    val listMk: List<Matakuliah> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)





