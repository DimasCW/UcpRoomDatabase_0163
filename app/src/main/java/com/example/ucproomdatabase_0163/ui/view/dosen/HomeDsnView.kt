package com.example.ucproomdatabase_0163.ui.view.Dsn


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucproomdatabase_0163.R
import com.example.ucproomdatabase_0163.data.entity.Dosen
import com.example.ucproomdatabase_0163.ui.costumwidget.CstTopAppBar
import com.example.ucproomdatabase_0163.ui.costumwidget.FontBevietnampro
import com.example.ucproomdatabase_0163.ui.view.matakuliah.ItemDetailMk
import com.example.ucproomdatabase_0163.ui.viewModel.dosen.HomeDsnViewModel
import com.example.ucproomdatabase_0163.ui.viewModel.dosen.HomeUiState
import com.example.ucproomdatabase_0163.ui.viewModel.dosen.PenyediaDsnViewModel
import com.example.ucproomdatabase_0163.ui.viewModel.matakuliah.DetailUiState
import com.example.ucproomdatabase_0163.ui.viewModel.matakuliah.toMatakuliahEntity


import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeDsnView(
    viewModel: HomeDsnViewModel = viewModel(factory = PenyediaDsnViewModel.Factory),
    onAddDsn: () -> Unit = { },
    onDetailClick: (String) -> Unit = { },
    modifier: Modifier = Modifier,
) {
    Scaffold (
        topBar = {
            CstTopAppBar(judul = "Daftar Dosen",
                showBackButton = false,
                onBack = { },
                modifier = modifier)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddDsn,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp),
                containerColor = Color(0xFFFFC441)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Dosen",
                )
            }
        }
    ){
            innerPadding ->
        val homeUiState by viewModel.homeUiState.collectAsState()

        BodyHomeDsnView(
            homeUiState = homeUiState,
            onClick = {
                onDetailClick(it)
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyHomeDsnView(
    homeUiState: HomeUiState,
    onClick: (String) -> Unit = { },
    modifier: Modifier = Modifier
){
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    when{
        homeUiState.isLoading -> {
            //Menampilkan indikator loading
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }

        homeUiState.isError -> {
            //Menampilkan pesan error
            LaunchedEffect(homeUiState.errorMessage) {
                homeUiState.errorMessage?.let { message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                }
            }
        }

        homeUiState.listDsn.isEmpty() -> {
            //Menampilkan pesan jika data kosong
            Box(modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Tidak ada data dosen.",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        else -> {
            //Menampilkan daftar Dsn
            listDosen(
                listDsn = homeUiState.listDsn,
                onClick = {
                    onClick(it)
                    println(
                        it
                    )
                },
                modifier = modifier
            )
        }
    }

}


@Composable
fun listDosen(
    listDsn : List<Dosen>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = {}
){
    LazyColumn (
        modifier = modifier
    ){
        items(
            items = listDsn,
            itemContent = { dsn ->
                CardDsn(
                    dsn = dsn,
                    onClick = { onClick(dsn.nidn)}
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardDsn(
    dsn: Dosen,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
){
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        // Gunakan Box untuk membuat layer
        Box {
            // Gambar latar belakang
            Image(
                painter = painterResource(id = R.drawable.bgcarddsn), // Ganti dengan resource gambar Anda
                contentDescription = "Background Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .matchParentSize(), // Gambar memenuhi seluruh Card
                contentScale = ContentScale.Crop // Mengatur gambar agar memenuhi Card secara proporsional
            )

            // Konten teks di atas gambar
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterStart) // Mengatur posisi konten
            ) {
                DetailRow(
                    label = "Nama",
                    value = dsn.nama
                )
                DetailRow(
                    label = "Nidn",
                    value = dsn.nidn
                )
                DetailRow(
                    label = "Jenis Kelamin",
                    value = dsn.jenisKelamin
                )

            }
        }
    }

}

@Composable
fun DetailRow(label: String, value: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = label,
            modifier = Modifier
                .weight(2f) // Memberikan bobot agar label mengambil ruang yang cukup
                .align(Alignment.CenterVertically),
            fontSize = 16.sp,
            color = Color(0xFF000000),
            fontWeight = FontWeight.Light,
            fontFamily = FontBevietnampro
        )
        Text(
            text = ":",
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .align(Alignment.CenterVertically), // Memberikan jarak horizontal di sekitar tanda ":"
            fontSize = 16.sp,
            color = Color(0xFF000000),
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontBevietnampro
        )
        Text(
            text = value,
            modifier = Modifier
                .weight(2f) // Memberikan ruang lebih untuk value agar tidak terlalu ke kanan
                .align(Alignment.CenterVertically)
                .padding(end = 16.dp), // Memberikan jarak dari tepi kanan
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = Color(0xFF000000),
            fontFamily = FontBevietnampro
        )
    }
}


