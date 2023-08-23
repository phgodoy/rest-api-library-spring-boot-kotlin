package library.library.book

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "books")
data class Book(
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
        val title: String,
        val author: String,
        val publisher: String,
        val isbn: String,
        val genre: String,
        val synopsis: String?,
        val cover: ByteArray?,
        val status: Int,
        val createdAt: Timestamp,
        val updatedAt: Timestamp?
)