package library.library.fines

import jakarta.persistence.*
import library.library.loan.Loan
import java.math.BigDecimal
import java.sql.Timestamp

@Entity
@Table(name = "fines")
data class Fines (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        @ManyToOne
        @JoinColumn(name = "loan_id", referencedColumnName = "id")
        val loan: Loan,
        val amount: BigDecimal,
        val createdAt: Timestamp,
        val updatedAt: Timestamp?
)
