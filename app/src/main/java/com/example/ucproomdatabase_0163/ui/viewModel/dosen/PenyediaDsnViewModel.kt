package com.example.ucproomdatabase_0163.ui.viewModel.dosen

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucproomdatabase_0163.MkApp

object PenyediaDsnViewModel {
    val Factory = viewModelFactory {
        initializer {
            DosenViewModel(
                MkApp().containerApp.repositoryDsn
            )
        }
        initializer{
            HomeDsnViewModel(
                MkApp() .containerApp.repositoryDsn
            )
        }

        initializer {
            DetailDsnViewModel(
                createSavedStateHandle(),
                MkApp() .containerApp.repositoryDsn
            )
        }


    }
}

fun CreationExtras.MkApp(): MkApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MkApp)

