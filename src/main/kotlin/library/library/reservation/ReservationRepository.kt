package library.library.reservation

import org.springframework.data.repository.CrudRepository

interface ReservationRepository : CrudRepository<Reservation, Long>