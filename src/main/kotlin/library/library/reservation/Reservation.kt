package library.library.reservation

import java.sql.Timestamp
import java.time.LocalDate
import jakarta.persistence.*
import library.library.book.Book
import library.library.user.User

@Entity
@Table(name = "reservations")
data class Reservation(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        @Column(name = "user_id")
        val userId: Long,
        @Column(name = "book_id")
        val bookId: Long,
        val reservationDate: LocalDate,
        val createdAt: Timestamp,
        val updatedAt: Timestamp?
)
