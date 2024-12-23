package com.example.ucproomdatabase_0163.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.ucproomdatabase_0163.R


@Composable
fun BerandaView(
    onMatakuliah: () -> Unit,
    onDosen: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(
            text = "Beranda",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp // Mengatur ukuran font menjadi lebih besar
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp) // Menambahkan jarak bawah
        )
        // Box pertama dengan background foto, border radius, dan shadow
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable {
                    // Tindakan untuk box pertama
                }
                .height(200.dp) // Sesuaikan tinggi box sesuai kebutuhan
                .clip(RoundedCornerShape(16.dp)) // Menambahkan border radius
                .shadow(elevation = 8.dp, shape = RoundedCornerShape(16.dp)) // Menambahkan shadow
        ) {
            // Set background image
            Image(
                painter = painterResource(id = R.drawable.matakuliah), // Ganti dengan ID gambar yang sesuai
                contentDescription = "Background Image",
                modifier = Modifier.matchParentSize(), // Membuat gambar memenuhi seluruh Box
                contentScale = ContentScale.Crop // Mengatur gambar agar memenuhi box
            )

            // Button di tengah kanan sedikit
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd) // Posisikan button di tengah kanan
                    .padding(end = 62.dp) // Menurunkan padding agar tidak terlalu kekanan
            ) {
                Button(
                    onClick = { onMatakuliah()},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFC441) // Mengganti warna button menjadi #ffc441
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.height(40.dp)
                ) {
                    BasicText(
                        text = "Cek Data",
                        style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold)
                    )
                }
            }
        }

        // Jarak antara box pertama dan kedua
        Spacer(modifier = Modifier.height(16.dp))

        // Box kedua dengan background foto, border radius, dan shadow
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable {
                    // Tindakan untuk box kedua
                }
                .height(200.dp) // Sesuaikan tinggi box sesuai kebutuhan
                .clip(RoundedCornerShape(16.dp)) // Menambahkan border radius
                .shadow(elevation = 8.dp, shape = RoundedCornerShape(16.dp)) // Menambahkan shadow
        ) {
            // Set background image
            Image(
                painter = painterResource(id = R.drawable.dosen), // Ganti dengan ID gambar yang sesuai
                contentDescription = "Background Image",
                modifier = Modifier.matchParentSize(), // Membuat gambar memenuhi seluruh Box
                contentScale = ContentScale.Crop // Mengatur gambar agar memenuhi box
            )

            // Button di tengah kanan sedikit
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd) // Posisikan button di tengah kanan
                    .padding(end = 62.dp) // Menurunkan padding agar tidak terlalu kekanan
            ) {
                Button(
                    onClick = { /* Tindakan untuk button kedua */onDosen() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFC441) // Mengganti warna button menjadi #ffc441
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.height(40.dp)
                ) {
                    BasicText(
                        text = "Cek Data",
                        style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold)
                    )
                }
            }
        }
    }
}