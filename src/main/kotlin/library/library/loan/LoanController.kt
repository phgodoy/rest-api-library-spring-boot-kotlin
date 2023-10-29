package library.library.book

import library.library.data.vo.v1.FinesVO
import library.library.data.vo.v1.LoanVO
import library.library.fines.Fines
import library.library.loan.Loan
import library.library.loan.LoanRepository
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/loans")
class LoanController(
        @Autowired private val loanRepository: LoanRepository,
        @Autowired private val modelMapper: ModelMapper
) {

    @GetMapping("")
    fun getAllLoans(): List<LoanVO> {
        val loan: List<Loan> = loanRepository.findAll().toList()

        return loan.map { loan ->
            modelMapper.map(loan, LoanVO::class.java)
        }
    }

    @PostMapping("")
    fun createLoan(@RequestBody loan: Loan): ResponseEntity<LoanVO> {

        val createdLoan = loanRepository.save(loan)
        return ResponseEntity(modelMapper.map(createdLoan, LoanVO::class.java), HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getLoanById(@PathVariable("id") loanId: Long): ResponseEntity<LoanVO> {
        val loan = loanRepository.findById(loanId)
        return if (loan != null) ResponseEntity(modelMapper.map(loan.get(), LoanVO::class.java), HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping("/{id}")
    fun updateLoanById(@PathVariable("id") loanId: Long, @RequestBody loan: Loan): ResponseEntity<LoanVO> {
        val existingLoan = loanRepository.findById(loanId).orElse(null)

        if (existingLoan == null) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

        val updatedLoan = existingLoan.copy(returnDate = loan.returnDate)
        loanRepository.save(updatedLoan)
        return ResponseEntity(modelMapper.map(updatedLoan, LoanVO::class.java), HttpStatus.OK)
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
