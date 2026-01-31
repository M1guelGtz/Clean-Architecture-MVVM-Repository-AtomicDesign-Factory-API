package com.m1guelgtz.templatecarsapi.Demo.Core.rutes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
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
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.ViewModels.LibraryViewModel

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController(), appContainer: AppConteiner) {
    val libraryModule = remember { BooksModule(appContainer) }
    val libraryViewModelFactory = remember { libraryModule.provideBooksViewModelFactory() }
    val libraryViewModel: LibraryViewModel = viewModel(factory = libraryViewModelFactory)
    val libraryUiState by libraryViewModel.uiState.collectAsState()
    
    NavHost(
        navController = navController,
        startDestination = RutaInicio.ruta
    ) {
        composable(RutaInicio.ruta) {
            LibraryScreen(libraryViewModel, navController)
        }

        composable(
            route = RutaDetalles.ruta,
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            val factory = libraryModule.provideBookDetailsViewModelFactory(
                bookId = id,
                cachedBooks = libraryUiState.book
            )
            DetallesScreen(id, navController, factory)
        }


    }
}