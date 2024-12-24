package com.example.ucproomdatabase_0163.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pertemuan9_roomdatabase.ui.view.mahasiswa.UpdateMkView
import com.example.ucproomdatabase_0163.ui.view.BerandaView
import com.example.ucproomdatabase_0163.ui.view.Dsn.HomeDsnView
import com.example.ucproomdatabase_0163.ui.view.dosen.DestinasiInsertDsn
import com.example.ucproomdatabase_0163.ui.view.dosen.DetailDsnView
import com.example.ucproomdatabase_0163.ui.view.dosen.InsertDsnView
import com.example.ucproomdatabase_0163.ui.view.matakuliah.DestinasiInsert
import com.example.ucproomdatabase_0163.ui.view.matakuliah.DetailMkView
import com.example.ucproomdatabase_0163.ui.view.matakuliah.HomeMkView
import com.example.ucproomdatabase_0163.ui.view.matakuliah.InsertMkView




@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = DestinasiBeranda.route,
        modifier = Modifier.padding()
    ) {
        composable(
            route = DestinasiBeranda.route
        ){
            BerandaView(
                onMatakuliah = {
                    navController.navigate(DestinasiHome.route)
                },
                onDosen = {
                    navController.navigate(DestinasiHomeDsn.route)
                }

            )
        }

        composable(route = DestinasiHome.route){
            HomeMkView(

            )
        }
        composable(route = DestinasiHomeDsn.route){
            HomeDsnView(

            )
        }

        composable(
            route = DestinasiHomeDsn.route
        ){
            HomeDsnView(
                onDetailClick = { nidn ->
                    navController.navigate("${DestinasiDetailDsn.route}/$nidn")
                    println(
                        "PengelolaHalaman: nidn = $nidn"
                    )
                },
                onAddDsn = {
                    navController.navigate(DestinasiInsertDsn.route)
                },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiHome.route
        ){
            HomeMkView(
                onDetailClick = { kode ->
                    navController.navigate("${DestinasiDetail.route}/$kode")
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
            route = DestinasiInsertDsn.route
        ) {
            InsertDsnView(
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
            DestinasiDetailDsn.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailDsn.NIDN){
                    type = NavType.StringType
                }
            )
        ){
            val nidn = it.arguments?.getString(DestinasiDetailDsn.NIDN)
            nidn?.let { nidn ->
                DetailDsnView(
                    onBack = {
                        navController.popBackStack()
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(
            DestinasiDetail.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetail.KODE){
                    type = NavType.StringType
                }
            )
        ){
            val kode = it.arguments?.getString(DestinasiDetail.KODE)
            kode?.let { kode ->
                DetailMkView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdate.route}/$it")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(
            DestinasiUpdate.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdate.KODE){
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