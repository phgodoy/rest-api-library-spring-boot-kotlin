package library.library.user

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val name: String,
    val email: String,
    val addres_id: Int,
    val phone: String,
    val identification_code: String,
    val created_at: java.sql.Timestamp,
    val updated_at: java.sql.Timestamp?
)