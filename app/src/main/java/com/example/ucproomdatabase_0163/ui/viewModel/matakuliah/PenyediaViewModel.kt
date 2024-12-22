package com.example.ucproomdatabase_0163.ui.viewModel.matakuliah
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucproomdatabase_0163.MkApp

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            MataKuliahViewModel(
                MkApp().containerApp.repositoryMk
            )
        }
        initializer{
            HomeMkViewModel(
                MkApp() .containerApp.repositoryMk
            )
        }

        initializer {
            DetailMkViewModel(
                createSavedStateHandle(),
                MkApp() .containerApp.repositoryMk
            )
        }

        initializer {
            UpdateMkViewModel(
                createSavedStateHandle(),
                MkApp() .containerApp.repositoryMk,
            )
        }
    }
}

fun CreationExtras.MkApp(): MkApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MkApp)

