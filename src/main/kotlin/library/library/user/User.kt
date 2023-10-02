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
        val addres_id: Long,
        val phone: String,
        val identificationCode: String,
        val createdAt: Timestamp,
        val updatedAt: Timestamp?
)
{
        constructor(
                name: String,
                email: String,
                addres_id: Long,
                phone: String,
                identificationCode: String,
                createdAt: Timestamp,
                updatedAt: Timestamp?
        ) : this(
                id = 0,
                name = name,
                email = email,
                addres_id = addres_id,
                phone = phone,
                identificationCode = identificationCode,
                createdAt = createdAt,
                updatedAt = updatedAt
        )
}
