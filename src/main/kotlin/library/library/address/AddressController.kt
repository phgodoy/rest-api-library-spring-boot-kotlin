package library.library.address

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/addresses")
class AddressController(@Autowired private val addressRepository: AddressRepository) {

    @GetMapping("")
    fun getAllAddresses(): List<Address> =
            addressRepository.findAll().toList()

    @PostMapping("")
    fun createAddress(@RequestBody address: Address): ResponseEntity<Address> {
        val createdAddress = addressRepository.save(address)
        return ResponseEntity(createdAddress, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getAddressById(@PathVariable("id") addressId: Long): ResponseEntity<Address> {
        val address = addressRepository.findById(addressId).orElse(null)
        return if (address != null) ResponseEntity(address, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping("/{id}")
    fun updateAddressById(@PathVariable("id") addressId: Long, @RequestBody address: Address): ResponseEntity<Address> {

        val existingAddress = addressRepository.findById(addressId).orElse(null)

        if (existingAddress == null) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

        val updatedAddress = existingAddress.copy(
                street = address.street,
                number = address.number,
                neighbourhood = address.neighbourhood,
                complement = address.complement,
                postalCode = address.postalCode,
                city = address.city,
                state = address.state,
                latitude = address.latitude,
                longitude = address.longitude,
                status = address.status
        )
        addressRepository.save(updatedAddress)
        return ResponseEntity(updatedAddress, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteAddressById(@PathVariable("id") addressId: Long): ResponseEntity<Void> {
        if (!addressRepository.existsById(addressId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        addressRepository.deleteById(addressId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
