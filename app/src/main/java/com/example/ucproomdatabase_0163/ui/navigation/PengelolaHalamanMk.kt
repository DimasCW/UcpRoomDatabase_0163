package com.example.ucproomdatabase_0163.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pertemuan9_roomdatabase.ui.view.mahasiswa.UpdateMkView
import com.example.ucproomdatabase_0163.ui.view.matakuliah.DestinasiInsert
import com.example.ucproomdatabase_0163.ui.view.matakuliah.DetailMkView
import com.example.ucproomdatabase_0163.ui.view.matakuliah.HomeMkView
import com.example.ucproomdatabase_0163.ui.view.matakuliah.InsertMkView


@Composable
fun PengelolaHalamanMk(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController, startDestination = DestinasiHomeMk.route
    ) {
        composable(
            route = DestinasiHomeMk.route
        ){
            HomeMkView(
                onDetailClick = { kode ->
                    navController.navigate("${DestinasiDetailMk.route}/$kode")
                    println(
                        "PengelolaHalaman: kode = $kode"
                    )
                },
                onAddMk = {
                    navController.navigate(DestinasiInsert.route)
                },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiInsert.route
        ) {
            InsertMkView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }

        composable(
            DestinasiDetailMk.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailMk.KODE){
                    type = NavType.StringType
                }
            )
        ){
            val kode = it.arguments?.getString(DestinasiDetailMk.KODE)
            kode?.let { kode ->
                DetailMkView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateMk.route}/$it")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(
            DestinasiUpdateMk.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateMk.KODE){
                    type = NavType.StringType
                }
            )
        ){
            UpdateMkView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }
    }
}