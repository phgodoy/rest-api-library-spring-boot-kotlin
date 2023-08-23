package library.library.reservation

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/reservations")
class ReservationController(@Autowired private val reservationRepository: ReservationRepository) {

    @GetMapping("")
    fun getAllReservations(): List<Reservation> =
            reservationRepository.findAll().toList()

    @PostMapping("")
    fun createReservation(@RequestBody reservation: Reservation): ResponseEntity<Reservation> {
        val createdReservation = reservationRepository.save(reservation)
        return ResponseEntity(createdReservation, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getReservationById(@PathVariable("id") reservationId: Long): ResponseEntity<Reservation> {
        val reservation = reservationRepository.findById(reservationId).orElse(null)
        return if (reservation != null) ResponseEntity(reservation, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping("/{id}")
    fun updateReservationById(@PathVariable("id") reservationId: Long, @RequestBody reservation: Reservation): ResponseEntity<Reservation> {

        val existingReservation = reservationRepository.findById(reservationId).orElse(null)

        if (existingReservation == null) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

        val updatedReservation = existingReservation.copy(reservationDate = reservation.reservationDate)
        reservationRepository.save(updatedReservation)
        return ResponseEntity(updatedReservation, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteReservationById(@PathVariable("id") reservationId: Long): ResponseEntity<Void> {
        if (!reservationRepository.existsById(reservationId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        reservationRepository.deleteById(reservationId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
