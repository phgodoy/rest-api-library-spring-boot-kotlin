package library.library.data.vo.v1

import library.library.book.Book
import library.library.user.User
import java.sql.Timestamp
import java.time.LocalDate


data class LoanVO (
        val id: Long,
        val userId: Long, // Representa o ID do usuário
        val bookId: Long, // Representa o ID do livro
        val loanDate: LocalDate,
        val dueDate: LocalDate,
        val returnDate: LocalDate?,
        val createdAt: Timestamp,
        val updatedAt: Timestamp?
) {
    // Construtor sem argumentos
    constructor() : this(0, 0, 0, LocalDate.now(), LocalDate.now(), null, Timestamp(System.currentTimeMillis()), null)
}