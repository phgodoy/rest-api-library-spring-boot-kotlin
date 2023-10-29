package library.library.reservation

import library.library.data.vo.v1.LoanVO
import library.library.data.vo.v1.ReservationVO
import library.library.loan.Loan
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/reservations")
class ReservationController(
        @Autowired private val reservationRepository: ReservationRepository,
        @Autowired private val modelMapper: ModelMapper
) {

    @GetMapping("")
    fun getAllReservations(): List<ReservationVO> {
        val reservation: List<Reservation> = reservationRepository.findAll().toList()

        return reservation.map { reservation ->
            modelMapper.map(reservation, ReservationVO::class.java)
        }
    }

    @PostMapping("")
    fun createReservation(@RequestBody reservation: Reservation): ResponseEntity<ReservationVO> {
        val createdReservation = reservationRepository.save(reservation)
        return ResponseEntity(modelMapper.map(createdReservation, ReservationVO::class.java), HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getReservationById(@PathVariable("id") reservationId: Long): ResponseEntity<ReservationVO> {
        val reservation = reservationRepository.findById(reservationId)
        return if (reservation != null) ResponseEntity(modelMapper.map(reservation.get(), ReservationVO::class.java), HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping("/{id}")
    fun updateReservationById(@PathVariable("id") reservationId: Long, @RequestBody reservation: Reservation): ResponseEntity<ReservationVO> {

        val existingReservation = reservationRepository.findById(reservationId).orElse(null)

        if (existingReservation == null) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

        val updatedReservation = existingReservation.copy(reservationDate = reservation.reservationDate)
        reservationRepository.save(updatedReservation)
        return ResponseEntity(modelMapper.map(updatedReservation, ReservationVO::class.java), HttpStatus.OK)
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
