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
        @ManyToOne
        @JoinColumn(name = "user_id", referencedColumnName = "id")
        val user: User,
        @ManyToOne
        @JoinColumn(name = "book_id", referencedColumnName = "id")
        val book: Book,
        val reservationDate: LocalDate,
        val createdAt: Timestamp,
        val updatedAt: Timestamp?
)
