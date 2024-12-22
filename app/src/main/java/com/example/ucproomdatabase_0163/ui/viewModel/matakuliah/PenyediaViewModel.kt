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
            MatakuliahViewModel(
                MkApp().containerAppMk.repositoryMk
            )
        }
        initializer{
            HomeMkViewModel(
                MkApp() .containerAppMk.repositoryMk
            )
        }

        initializer {
            DetailMkViewModel(
                createSavedStateHandle(),
                MkApp() .containerAppMk.repositoryMk
            )
        }

        initializer {
            UpdateMkViewModel(
                createSavedStateHandle(),
                MkApp() .containerAppMk.repositoryMk,
            )
        }
    }
}

fun CreationExtras.MkApp(): MkApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MkApp)

