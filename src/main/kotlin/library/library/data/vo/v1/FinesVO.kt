package library.library.data.vo.v1

import library.library.loan.Loan
import java.math.BigDecimal
import java.sql.Timestamp

data class FinesVO (
        val id: Long = 0,
        val loan_id: Long = 0,
        val amount: BigDecimal,
        val createdAt: Timestamp,
        val updatedAt: Timestamp?
) {
    // Construtor sem argumentos
    constructor() : this(0, 0, BigDecimal.ZERO, Timestamp(System.currentTimeMillis()), null)
}
