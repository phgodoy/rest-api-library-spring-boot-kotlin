package library.library.user

import com.fasterxml.jackson.databind.ObjectMapper
import library.library.address.AddressRepository
import library.library.data.vo.v1.UserVO
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(
        @Autowired private val userRepository: UserRepository,
        @Autowired private val addressRepository: AddressRepository,
        @Autowired private val modelMapper: ModelMapper
) {

    @GetMapping("")
    fun getAllUsers(): List<UserVO> {
        val users: List<User> = userRepository.findAll().toList()

        return users.map { user ->
            modelMapper.map(user, UserVO::class.java)
        }
    }

    @PostMapping("")
    fun createUser(@RequestBody user: User): ResponseEntity<UserVO> {
        val createdUser = userRepository.save(user)

        return ResponseEntity(modelMapper.map(createdUser, UserVO::class.java), HttpStatus.CREATED)
    }

    @PostMapping("/user-with-address")
    fun createUserWithAddress(@RequestBody requestData: Map<String, Any>): ResponseEntity<UserVO> {
        try {
            val userJson = ObjectMapper().writeValueAsString(requestData["user"])

            val user = ObjectMapper().readValue(userJson, User::class.java)
            val createdUser = userRepository.save(user)
            return ResponseEntity(modelMapper.map(createdUser, UserVO::class.java), HttpStatus.CREATED)
        } catch (e: Exception) {
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}