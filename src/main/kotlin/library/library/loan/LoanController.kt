package library.library.book

import library.library.loan.Loan
import library.library.loan.LoanRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/loans")
class LoanController(@Autowired private val loanRepository: LoanRepository) {

    @GetMapping("")
    fun getAllLoans(): List<Loan> =
            loanRepository.findAll().toList()

    @PostMapping("")
    fun createLoan(@RequestBody loan: Loan): ResponseEntity<Loan> {
        val createdLoan = loanRepository.save(loan)
        return ResponseEntity(createdLoan, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getLoanById(@PathVariable("id") loanId: Long): ResponseEntity<Loan> {
        val loan = loanRepository.findById(loanId).orElse(null)
        return if (loan != null) ResponseEntity(loan, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping("/{id}")
    fun updateLoanById(@PathVariable("id") loanId: Long, @RequestBody loan: Loan): ResponseEntity<Loan> {

        val existingLoan = loanRepository.findById(loanId).orElse(null)

        if (existingLoan == null) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

        val updatedLoan = existingLoan.copy(returnDate = loan.returnDate)
        loanRepository.save(updatedLoan)
        return ResponseEntity(updatedLoan, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteLoanById(@PathVariable("id") loanId: Long): ResponseEntity<Void> {
        if (!loanRepository.existsById(loanId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        loanRepository.deleteById(loanId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
