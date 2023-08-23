package library.library.address
import jakarta.persistence.*
import library.library.user.User
import java.sql.Timestamp

@Entity
@Table(name = "address")
data class Address(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,
        @ManyToOne
        @JoinColumn(name = "user_id", referencedColumnName = "id")
        val user: User,
        val street: String,
        val number: Int,
        val neighbourhood: String,
        val complement: String,
        val postalCode: Int,
        val city: String,
        val state: String,
        val latitude: Double,
        val longitude: Double,
        val status: Int,
        val createdAt: Timestamp,
        val updatedAt: Timestamp?
)