package library.library.book

import library.library.data.vo.v1.BookVO
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/books")
class BookController(
        @Autowired private val bookRepository: BookRepository,
        @Autowired private val modelMapper: ModelMapper
) {

    @GetMapping("")
    fun getAllBooks(): List<BookVO> {
        val book: List<Book> = bookRepository.findAll().toList()

        return book.map { book ->
            modelMapper.map(book, BookVO::class.java)
        }
    }

    @PostMapping("")
    fun createBook(@RequestBody book: Book): ResponseEntity<BookVO> {
        val createdBook = bookRepository.save(book)
        return ResponseEntity(modelMapper.map(createdBook, BookVO::class.java), HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getBookById(@PathVariable("id") bookId: Int): ResponseEntity<BookVO> {
        val book = bookRepository.findById(bookId.toLong())
        return if (book != null) ResponseEntity(modelMapper.map(book.get(), BookVO::class.java), HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping("/{id}")
    fun updateBookById(@PathVariable("id") bookId: Int, @RequestBody book: Book): ResponseEntity<BookVO> {

        val existingUser = bookRepository.findById(bookId.toLong()).orElse(null)

        if (existingUser == null) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

        val updatedBook = existingUser.copy(title = book.title, author = book.author)
        bookRepository.save(updatedBook)
        return ResponseEntity(modelMapper.map(updatedBook, BookVO::class.java), HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable("id") bookId: Int): ResponseEntity<Void> {
        if (!bookRepository.existsById(bookId.toLong())) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        bookRepository.deleteById(bookId.toLong())
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
