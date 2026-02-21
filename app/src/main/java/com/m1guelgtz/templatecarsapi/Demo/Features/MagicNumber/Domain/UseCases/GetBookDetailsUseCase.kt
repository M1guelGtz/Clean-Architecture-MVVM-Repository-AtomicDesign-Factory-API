package com.m1guelgtz.templatecarsapi.Demo.Features.MagicNumber.Domain.UseCases

import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.Entities.Book
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.Repository.BooksRepository

class GetBookDetailsUseCase(
    private val repository: BooksRepository
) {
    suspend operator fun invoke(bookId: Int, cachedBooks: List<Book>): Result<Book> {
        return try {
            // Validar que el índice sea válido
            if (cachedBooks.isEmpty()) {
                return Result.failure(Exception("No hay libros disponibles. Por favor, realiza una búsqueda primero."))
            }
            
            if (bookId < 0 || bookId >= cachedBooks.size) {
                return Result.failure(Exception("Libro no encontrado. Índice: $bookId, Total: ${cachedBooks.size}"))
            }
            
            // Buscar en la lista cacheada
            val book = cachedBooks[bookId]
            Result.success(book)
        } catch (e: Exception) {
            Result.failure(Exception("Error al obtener detalles del libro: ${e.message}"))
        }
    }
}
