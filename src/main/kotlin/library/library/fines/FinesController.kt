package library.library.fines

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/fines")
class FinesController(@Autowired private val finesRepository: FinesRepository) {

    @GetMapping("")
    fun getAllFines(): List<Fines> =
            finesRepository.findAll().toList()

    @PostMapping("")
    fun createFines(@RequestBody fines: Fines): ResponseEntity<Fines> {
        val createdFines = finesRepository.save(fines)
        return ResponseEntity(createdFines, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getFinesById(@PathVariable("id") finesId: Long): ResponseEntity<Fines> {
        val fines = finesRepository.findById(finesId).orElse(null)
        return if (fines != null) ResponseEntity(fines, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping("/{id}")
    fun updateFinesById(@PathVariable("id") finesId: Long, @RequestBody fines: Fines): ResponseEntity<Fines> {

        val existingFines = finesRepository.findById(finesId).orElse(null)

        if (existingFines == null) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

        val updatedFines = existingFines.copy(
                amount = fines.amount
        )
        finesRepository.save(updatedFines)
        return ResponseEntity(updatedFines, HttpStatus.OK)
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
