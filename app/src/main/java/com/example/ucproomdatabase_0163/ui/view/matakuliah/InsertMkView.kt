package com.example.ucproomdatabase_0163.ui.view.matakuliah

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucproomdatabase_0163.data.DosenDD
import com.example.ucproomdatabase_0163.data.entity.Dosen
import com.example.ucproomdatabase_0163.ui.costumwidget.CstTopAppBar
import com.example.ucproomdatabase_0163.ui.costumwidget.FontBevietnampro
import com.example.ucproomdatabase_0163.ui.navigation.AlamatNavigasi
import com.example.ucproomdatabase_0163.ui.viewModel.matakuliah.FormErrorState
import com.example.ucproomdatabase_0163.ui.viewModel.matakuliah.MatakuliahEvent
import com.example.ucproomdatabase_0163.ui.viewModel.matakuliah.MatakuliahViewModel
import com.example.ucproomdatabase_0163.ui.viewModel.matakuliah.MkUiState
import com.example.ucproomdatabase_0163.ui.viewModel.matakuliah.PenyediaViewModel
import kotlinx.coroutines.flow.map

import kotlinx.coroutines.launch

@Composable
fun InsertBodyMk(
    modifier: Modifier = Modifier,
    onValueChange: (MatakuliahEvent) -> Unit,
    uiState: MkUiState,
    onClick: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormMatakuliah(
            matakuliahEvent = uiState.matakuliahEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Simpan")
        }
    }
}

object DestinasiInsert : AlamatNavigasi {
    override val route: String = "insert_mk"
}

@Composable
fun InsertMkView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MatakuliahViewModel = viewModel(factory = PenyediaViewModel.Factory) // inisialisasi view model
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
                .padding(16.dp)
        ) {

            CstTopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Matakuliah",
                modifier = modifier
            )
            // isi body
            InsertBodyMk(
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
fun FormMatakuliah(
    matakuliahEvent: MatakuliahEvent = MatakuliahEvent(),
    onValueChange: (MatakuliahEvent) -> Unit,
    errorState: FormErrorState = FormErrorState(),
    modifier: Modifier = Modifier,
    dosenList: List<Dosen> = emptyList()
){
    var chosenDropdown by remember { mutableStateOf("") }
    val jenis = listOf("Laki-laki", "Perempuan")
    val semester = listOf("Ganjil", "Genap")
    val sks = listOf("1", "2","3")
    val expanded = remember { mutableStateOf(false) }
    val selectedDosen = remember { mutableStateOf(matakuliahEvent.dosenPengampu) }

    Column (
        modifier = modifier.fillMaxWidth()
    ){
        Text(
            text = "Nama Matakuliah",
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontBevietnampro
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.nama,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(nama = it))
            },
            label = { Text("Masukkan Nama Matakuliah") },
            isError = errorState.nama != null,
            placeholder = { Text("Aplikasi Pemrograman Web") },
        )
        Text(
            text = errorState.nama ?: "",
            color = Color.Red
        )
        Text(
            text = "Kode Matkuliah",
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontBevietnampro
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.kode,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(kode = it))
            },
            label = { Text("Masukkan Kode Matakuliah") },
            isError = errorState.kode != null,
            placeholder = { Text("MK001") },

        )
        Text(
            text = errorState.kode ?: "",
            color = Color.Red
        )

        Text(
            text = "Jenis Kelamin",
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontBevietnampro
            )
        Row (
            modifier = Modifier.fillMaxWidth()
        ){
            jenis.forEach { j ->
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){
                    RadioButton(
                        selected = matakuliahEvent.jenis == j,
                        onClick = {
                            onValueChange(matakuliahEvent.copy(jenis = j))
                        },
                    )
                    Text(text = j,)
                }
            }
        }
        Text(
            text = errorState.jenis ?: "",
            color = Color.Red
        )
        Text(
            text = "Jumlah Sks",
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontBevietnampro
            )
        Row (
            modifier = Modifier.fillMaxWidth(),
        ){
            sks.forEach { sks ->
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){
                    RadioButton(
                        selected = matakuliahEvent.sks == sks,
                        onClick = {
                            onValueChange(matakuliahEvent.copy(sks = sks))
                        },
                    )
                    Text(text = sks,)
                }
            }
        }
        Text(
            text = errorState.sks ?: "",
            color = Color.Red
        )
        Text(
            text = "semester",
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontBevietnampro
            )
        Row {
            semester.forEach { semester ->
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                ){
                    RadioButton(
                        selected = matakuliahEvent.semester == semester,
                        onClick = {
                            onValueChange(matakuliahEvent.copy(semester = semester))
                        },
                    )
                    Text(text = semester,)
                }
            }
        }
        Text(
            text = errorState.semester ?: "",
            color = Color.Red
        )
        val dosenNames = DosenDD.option.collectAsState(initial = emptyList()).value.map { it.nama }
        DynamicSelectTextField(
            selectedValue = chosenDropdown,
            options = dosenNames,
            label = "Dosen Pengampu",
            onValueChangedEvent = {
                chosenDropdown = it
                onValueChange(matakuliahEvent.copy(dosenPengampu = it))
            },


        )
        Text(
            text = errorState.dosenPengampu ?: "",
            color = Color.Red
        )

    }
}

