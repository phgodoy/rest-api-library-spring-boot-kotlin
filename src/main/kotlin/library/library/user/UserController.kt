package library.library.user

import library.library.dtos.UserWithAddress
import library.library.address.AddresRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.slf4j.LoggerFactory

@RestController
@RequestMapping("/api/users")
class UserController(@Autowired private val userRepository: UserRepository,
                     @Autowired private val addressRepository: AddresRepository) {

    @GetMapping("")
    fun getAllUsers(): List<User> =
            userRepository.findAll().toList()

    @PostMapping("")
    fun createUser(@RequestBody userWithAddress: UserWithAddress): ResponseEntity<User> {
        val user = userWithAddress.user
        val address = userWithAddress.address

        // Salve o endereço no banco de dados primeiro e obtenha o ID gerado
        val savedAddress = addressRepository.save(address)

        // Crie o usuário e associe o ID do endereço
        val createdUser = User(
                id = user.id,  // Você pode ajustar isso conforme a sua lógica de geração de IDs
                name = user.name,
                email = user.email,
                addres_id = savedAddress.id.toInt(),
                phone = user.phone,
                identification_code = user.identification_code,
                created_at = user.created_at,
                updated_at = user.updated_at
        )

        userRepository.save(createdUser)

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
}
