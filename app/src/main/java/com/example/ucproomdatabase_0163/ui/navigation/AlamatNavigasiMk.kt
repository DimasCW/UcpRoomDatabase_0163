package com.example.ucproomdatabase_0163.ui.navigation

interface AlamatNavigasiMk {
    val route: String
}

//------
object DestinasiHomeMk : AlamatNavigasiMk{
    override val route = "home"
}

object DestinasiDetailMk : AlamatNavigasiMk{
    override val route = "detail"
    const val KODE = "kode"
    val routesWithArg = "$route/{$KODE}"
}

object DestinasiUpdateMk : AlamatNavigasiMk {
    override val route = "update"
    const val KODE = "kode"
    val routesWithArg = "$route/{$KODE}"
}