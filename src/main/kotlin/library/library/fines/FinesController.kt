package library.library.fines

import library.library.book.Book
import library.library.data.vo.v1.BookVO
import library.library.data.vo.v1.FinesVO
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/fines")
class FinesController(
        @Autowired private val finesRepository: FinesRepository,
        @Autowired private val modelMapper: ModelMapper
) {

    @GetMapping("")
    fun getAllFines(): List<FinesVO> {
        val fines: List<Fines> = finesRepository.findAll().toList()

        return fines.map { fines ->
            modelMapper.map(fines, FinesVO::class.java)
        }
    }

    @PostMapping("")
    fun createFines(@RequestBody fines: Fines): ResponseEntity<FinesVO> {
        val createdFines = finesRepository.save(fines)
        return ResponseEntity(modelMapper.map(createdFines, FinesVO::class.java), HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getFinesById(@PathVariable("id") finesId: Long): ResponseEntity<FinesVO> {
        val fines = finesRepository.findById(finesId)
        return if (fines != null) ResponseEntity(modelMapper.map(fines.get(), FinesVO::class.java), HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping("/{id}")
    fun updateFinesById(@PathVariable("id") finesId: Long, @RequestBody fines: Fines): ResponseEntity<FinesVO> {

        val existingFines = finesRepository.findById(finesId).orElse(null)

        if (existingFines == null) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

        val updatedFines = existingFines.copy(
                amount = fines.amount
        )
        finesRepository.save(updatedFines)
        return ResponseEntity(modelMapper.map(updatedFines, FinesVO::class.java), HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteFinesById(@PathVariable("id") finesId: Long): ResponseEntity<Void> {
        if (!finesRepository.existsById(finesId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        finesRepository.deleteById(finesId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
