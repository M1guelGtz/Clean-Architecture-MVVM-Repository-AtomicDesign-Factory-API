package com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.ViewModels.LibraryViewModel
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.ViewModels.LibraryViewModelFactory

@Composable
fun LibraryScreen (
    factory: LibraryViewModelFactory
) {
    val viewModel: LibraryViewModel = viewModel (factory = factory)

}