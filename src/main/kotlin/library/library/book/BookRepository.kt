package library.library.book

import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<Book, Long>