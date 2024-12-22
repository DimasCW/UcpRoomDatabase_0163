package com.example.ucproomdatabase_0163.ui.navigation

interface AlamatNavigasiDosen {
    val route: String
}

//------
object DestinasiHomeDosen : AlamatNavigasiDosen{
    override val route = "home"
}

object DestinasiDetailDosen : AlamatNavigasiDosen{
    override val route = "detail"
    const val NIDN = "nidn"
    val routesWithArg = "$route/{$NIDN}"
}
