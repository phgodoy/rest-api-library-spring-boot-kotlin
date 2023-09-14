package library.library.book

import library.library.user.User
import library.library.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
//teste
@RestController
@RequestMapping("/api/books")
class BookController(@Autowired private val bookRepository: BookRepository) {

    @GetMapping("")
    fun getAllBooks(): List<Book> =
            bookRepository.findAll().toList()

    @PostMapping("")
    fun createUser(@RequestBody book: Book): ResponseEntity<Book> {
        val createdUser = bookRepository.save(book)
        return ResponseEntity(createdUser, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable("id") bookId: Int): ResponseEntity<Book> {
        val book = bookRepository.findById(bookId.toLong()).orElse(null)
        return if (book != null) ResponseEntity(book, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping("/{id}")
    fun updateUserById(@PathVariable("id") bookId: Int, @RequestBody book: Book): ResponseEntity<Book> {

        val existingUser = bookRepository.findById(bookId.toLong()).orElse(null)

        if (existingUser == null) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

        val updatedUser = existingUser.copy(title = book.title, author = book.author)
        bookRepository.save(updatedUser)
        return ResponseEntity(updatedUser, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable("id") bookId: Int): ResponseEntity<User> {
        if (!bookRepository.existsById(bookId.toLong())) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        bookRepository.deleteById(bookId.toLong())
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
