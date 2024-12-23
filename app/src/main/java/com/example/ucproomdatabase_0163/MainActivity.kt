package com.example.ucproomdatabase_0163


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.ucproomdatabase_0163.data.DosenDD
import com.example.ucproomdatabase_0163.ui.navigation.PengelolaHalaman
import com.example.ucproomdatabase_0163.ui.theme.UcpRoomDatabase_0163Theme
import com.example.ucproomdatabase_0163.ui.view.BerandaView
import com.example.ucproomdatabase_0163.ui.view.Dsn.HomeDsnView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UcpRoomDatabase_0163Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PengelolaHalaman(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        DosenDD.loadData(this)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UcpRoomDatabase_0163Theme {
        Greeting("Android")
    }
}