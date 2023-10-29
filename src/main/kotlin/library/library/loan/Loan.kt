package library.library.loan

import java.sql.Timestamp
import java.time.LocalDate
import jakarta.persistence.*
import library.library.book.Book
import library.library.user.User

@Entity
@Table(name = "loans")
data class Loan(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        @Column(name = "user_id") // Mapeia para o campo user_id no banco de dados
        val userId: Long, // Alterado de User para Long (user_id)
        @Column(name = "book_id") // Mapeia para o campo book_id no banco de dados
        val bookId: Long, // Alterado de Book para Long (book_id)
        val loanDate: LocalDate,
        val dueDate: LocalDate,
        val returnDate: LocalDate?,
        val createdAt: Timestamp,
        val updatedAt: Timestamp?
)


