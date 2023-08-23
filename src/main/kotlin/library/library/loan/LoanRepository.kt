package library.library.loan

import org.springframework.data.repository.CrudRepository

interface LoanRepository : CrudRepository<Loan, Long>