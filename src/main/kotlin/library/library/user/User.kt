package library.library.user

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "users")
data class User(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        val name: String,
        val email: String,
        var addres_id: Long,
        val phone: String,
        val identificationCode: String,
        val createdAt: Timestamp,
        val updatedAt: Timestamp?
)