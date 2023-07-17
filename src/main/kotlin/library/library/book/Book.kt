package library.library.book

import jakarta.persistence.*
import org.w3c.dom.Text

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
    val synopsis: Text,
    val cover: Byte,
    val status: Int,
    val created_at: java.sql.Timestamp,
    val updated_at: java.sql.Timestamp?
)