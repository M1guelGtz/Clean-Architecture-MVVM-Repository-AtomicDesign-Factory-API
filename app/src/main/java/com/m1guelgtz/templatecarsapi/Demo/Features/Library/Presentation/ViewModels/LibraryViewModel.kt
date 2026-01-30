package com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.UseCases.GetBooksUseCase

class LibraryViewModel (
    private val usecase : GetBooksUseCase
) : ViewModel() {
    private fun loadBooks (name: String){
        //cambiare el estado filtrando datos por nombre
    }
}