package library.library.address

import library.library.data.vo.v1.AddressVO
import library.library.data.vo.v1.UserVO
import library.library.user.User
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/addresses")
class AddressController(
        @Autowired private val addressRepository: AddressRepository,
        @Autowired private val modelMapper: ModelMapper
) {

    @GetMapping("")
    fun getAllAddresses(): List<AddressVO> {
        val address: List<Address> = addressRepository.findAll().toList()

        return address.map { address ->
            modelMapper.map(address, AddressVO::class.java)
        }
    }

    @PostMapping("")
    fun createAddress(@RequestBody address: Address): ResponseEntity<AddressVO> {
        val createdAddress = addressRepository.save(address)
        return ResponseEntity(modelMapper.map(createdAddress, AddressVO::class.java), HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getAddressById(@PathVariable("id") addressId: Long): ResponseEntity<AddressVO> {
        val address = addressRepository.findById(addressId)
        return if (address != null) ResponseEntity(modelMapper.map(address.get(), AddressVO::class.java), HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping("/{id}")
    fun updateAddressById(@PathVariable("id") addressId: Long, @RequestBody address: Address): ResponseEntity<AddressVO> {

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

        return ResponseEntity(modelMapper.map(updatedAddress, AddressVO::class.java), HttpStatus.OK)
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
