package com.m1guelgtz.templatecarsapi.Demo.Core.rutes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Screens.DetallesScreen
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Screens.LibraryScreen
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.ViewModels.BookDetailsViewModel
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.ViewModels.LibraryViewModel
import com.m1guelgtz.templatecarsapi.Demo.Features.MagicNumber.Presentation.Screens.MagicNumberScreen

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = RutaInicio // ruta serializable inicial
    ) {
        composable<RutaInicio> {
            val viewModel: LibraryViewModel = hiltViewModel()
            LibraryScreen(viewModel, navController)
        }

        composable<RutaGame> {
            MagicNumberScreen()
        }

        composable<RutaDetalles> {
            val detailsViewModel: BookDetailsViewModel = hiltViewModel()
            val libraryViewModel: LibraryViewModel = hiltViewModel() 
            val libraryUiState by libraryViewModel.uiState.collectAsState()

            LaunchedEffect(libraryUiState.book) {
                if (libraryUiState.book.isNotEmpty()) {
                    detailsViewModel.setCachedBooks(libraryUiState.book)
                }
            }
            
            DetallesScreen(detailsViewModel, navController)
        }
    }
}