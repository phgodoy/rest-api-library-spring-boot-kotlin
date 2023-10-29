package library.library.data.vo.v1

import library.library.book.Book
import library.library.user.User
import java.sql.Timestamp
import java.time.LocalDate

data class ReservationVO (
        val id: Long = 0,
        val user_id: Long = 0,
        val book_id: Long = 0,
        val reservationDate: LocalDate,
        val createdAt: Timestamp,
        val updatedAt: Timestamp?
) {
    // Construtor sem argumentos
    constructor() : this(0, 0, 0, LocalDate.now(), Timestamp(System.currentTimeMillis()), null)
}