package com.example.ucproomdatabase_0163.ui.view.dosen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucproomdatabase_0163.R
import com.example.ucproomdatabase_0163.ui.costumwidget.CstTopAppBar
import com.example.ucproomdatabase_0163.ui.navigation.AlamatNavigasiDsn
import com.example.ucproomdatabase_0163.ui.viewModel.dosen.DosenEvent
import com.example.ucproomdatabase_0163.ui.viewModel.dosen.DosenViewModel
import com.example.ucproomdatabase_0163.ui.viewModel.dosen.DsnUiState
import com.example.ucproomdatabase_0163.ui.viewModel.dosen.FormErrorState
import com.example.ucproomdatabase_0163.ui.viewModel.dosen.PenyediaDsnViewModel

import kotlinx.coroutines.launch
val FontBevietnampro = FontFamily(
    Font(R.font.bevietnampro) // Ganti dengan nama file font Anda
)
@Composable
fun InsertBodyDsn(
    modifier: Modifier = Modifier,
    onValueChange: (DosenEvent) -> Unit,
    uiState: DsnUiState,
    onClick: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormDosen(
            dosenEvent = uiState.dosenEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFC441), // Change the button background color
                contentColor = Color.White // Change the text color
            )
        ) {
            Text(
                text = "Simpan",
                style = TextStyle(
                    fontFamily = FontBevietnampro,// Set the custom font family
                    fontWeight = FontWeight.Bold, // Optional: Set the font weight
                    fontSize = 18.sp // Set the font size
                )
            )
        }
    }
}

object DestinasiInsertDsn : AlamatNavigasiDsn {
    override val route: String = "insert_dsn"
}

@Composable
fun InsertDsnView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DosenViewModel = viewModel(factory = PenyediaDsnViewModel.Factory) // inisialisasi view model
){
    val uiState = viewModel.uiState // Ambil UI state dari view model
    val snackbarHostState =  remember { SnackbarHostState() } // Snackbar state
    val coroutineScope = rememberCoroutineScope()

    // Observasi perubahan snackBarMessage
    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message) // tampilkan snackbar
                viewModel.resetSnackBarMessage()
            }
        }
    }
    Scaffold (
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) } // Tampilkan Snackbar di Scaffold
    ){ padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            CstTopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Dosen",
                modifier = modifier
            )
            // isi body
            InsertBodyDsn(
                uiState = uiState,
                onValueChange = { updateEvent ->
                    viewModel.updateState(updateEvent) // update state di view model
                },
                onClick = {
                    coroutineScope.launch {
                        viewModel.saveData() // simpan data
                    }
                    onNavigate()
                }
            )
        }
    }
}

@Composable
fun FormDosen(
    dosenEvent: DosenEvent = DosenEvent(),
    onValueChange: (DosenEvent) -> Unit,
    errorState: FormErrorState = FormErrorState(),
    modifier: Modifier = Modifier
){
    val jenisKelamin = listOf("Laki - laki", "Perempuan")

    Column (
        modifier = modifier.fillMaxWidth()
    ){
        Text(
            text = "Nama Dosen",
            fontWeight = FontWeight.SemiBold,
            fontFamily = com.example.ucproomdatabase_0163.ui.costumwidget.FontBevietnampro
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dosenEvent.nama,
            onValueChange = {
                onValueChange(dosenEvent.copy(nama = it))
            },
            label = { Text("Masukkan Nama") },
            isError = errorState.nama != null,
            placeholder = { Text("Masukkan nama") },
        )
        Text(
            text = errorState.nama ?: "",
            color = Color.Red
        )
        Text(
            text = "Nidn",
            fontWeight = FontWeight.SemiBold,
            fontFamily = com.example.ucproomdatabase_0163.ui.costumwidget.FontBevietnampro
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dosenEvent.nidn,
            onValueChange = {
                onValueChange(dosenEvent.copy(nidn = it))
            },
            label = { Text("Masukkan idn") },
            isError = errorState.nidn != null,
            placeholder = { Text("Masukkan Nidn") },

            )
        Text(
            text = errorState.nidn ?: "",
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Jenis Kelamin")
        Row (
            modifier = Modifier.fillMaxWidth()
        ){
            jenisKelamin.forEach { jk ->
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){
                    RadioButton(
                        selected = dosenEvent.jenisKelamin == jk,
                        onClick = {
                            onValueChange(dosenEvent.copy(jenisKelamin = jk))
                        },
                    )
                    Text(text = jk,)
                }
            }
            Text(
                text = errorState.nidn ?: "",
                color = Color.Red
            )
        }

    }
}