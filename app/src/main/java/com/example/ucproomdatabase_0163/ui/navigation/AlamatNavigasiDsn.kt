package com.example.ucproomdatabase_0163.ui.navigation

import com.example.ucproomdatabase_0163.ui.navigation.DestinasiDetailDsn.NIDN

interface AlamatNavigasiDsn {
    val route: String
}


object DestinasiHomeDsn : AlamatNavigasiDsn{
    override val route = "homeDsn"
}


object DestinasiDetailDsn : AlamatNavigasiDsn{
    override val route = "detailDsn"
    const val NIDN = "nidn"
    val routesWithArg = "$route/{$NIDN}"
}

