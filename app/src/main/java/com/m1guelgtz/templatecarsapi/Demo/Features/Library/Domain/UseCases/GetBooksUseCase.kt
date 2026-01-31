package com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.UseCases

import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.Entities.Book
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.Repository.BooksRepository

class GetBooksUseCase (
    private val repository: BooksRepository
) {
    suspend operator fun invoke(name: String) : Result<List<Book>> {
        return try {
            val books = repository.getBooks(name)
            val filteredBooks = books.filter { it.title.isNotBlank() }
            if (filteredBooks.isEmpty()){
                Result.failure(Exception("No hay Libros validos"))
            }
            else {
                // Devolver todos los libros ordenados por rating
                val sortedBooks = filteredBooks.sortedByDescending { it.rating }
                Result.success(sortedBooks)
            }
        } catch (e: Exception){
            Result.failure(e)
        }
    }
}
