package com.m1guelgtz.templatecarsapi.Demo.Core.rutes

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.m1guelgtz.templatecarsapi.Demo.Core.Di.AppConteiner
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Di.BooksModule
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Screens.DetallesScreen
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Screens.LibraryScreen
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.ViewModels.LibraryViewModelFactory

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController(), appContainer: AppConteiner) {
    NavHost(
        navController = navController,
        startDestination = RutaInicio.ruta
    ) {
        val libraryModule = BooksModule(appContainer)
        composable(RutaInicio.ruta) {
            LibraryScreen(libraryModule.provideBooksViewModelFactory(), navController)
        }

        composable(
            route = RutaDetalles.ruta,
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            DetallesScreen(id, navController)
        }


    }
}