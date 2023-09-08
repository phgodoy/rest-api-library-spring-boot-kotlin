package library.library.user

import com.fasterxml.jackson.databind.ObjectMapper
import library.library.address.Address
import library.library.address.AddressRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(
        @Autowired private val userRepository: UserRepository,
        @Autowired private val addressRepository: AddressRepository
) {

    @GetMapping("")
    fun getAllUsers(): List<User> =
            userRepository.findAll().toList()

    @PostMapping("")
    fun createUser(@RequestBody user: User): ResponseEntity<User> {
        val createdUser = userRepository.save(user)

        return ResponseEntity(createdUser, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable("id") userId: Int): ResponseEntity<User> {
        val user = userRepository.findById(userId.toLong()).orElse(null)
        return if (user != null) ResponseEntity(user, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping("/{id}")
    fun updateUserById(@PathVariable("id") userId: Int, @RequestBody user: User): ResponseEntity<User> {
        val existingUser = userRepository.findById(userId.toLong()).orElse(null)

        if (existingUser == null) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

        val updatedUser = existingUser.copy(name = user.name, email = user.email)
        userRepository.save(updatedUser)
        return ResponseEntity(updatedUser, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable("id") userId: Int): ResponseEntity<User> {
        if (!userRepository.existsById(userId.toLong())) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        userRepository.deleteById(userId.toLong())
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @PostMapping("/user-with-address")
    fun createUserWithAddress(@RequestBody requestData: Map<String, Any>): ResponseEntity<Any> {
        try {
            val userJson = ObjectMapper().writeValueAsString(requestData["user"])
            val addressJson = ObjectMapper().writeValueAsString(requestData["address"])

            val user = ObjectMapper().readValue(userJson, User::class.java)
            val address = ObjectMapper().readValue(addressJson, Address::class.java)

            val savedAddress = addressRepository.save(address)
            user.addres_id = savedAddress.id

            val createdUser = userRepository.save(user)
            return ResponseEntity(createdUser, HttpStatus.CREATED)
        } catch (e: Exception) {
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}
