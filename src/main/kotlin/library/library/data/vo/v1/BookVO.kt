package library.library.data.vo.v1

import java.sql.Timestamp

data class BookVO (
        var id: Long = 0,
        var title: String,
        var author: String,
        var publisher: String,
        var isbn: String,
        var genre: String,
        var synopsis: String?,
        var cover: ByteArray?,
        var status: Int = 0,
        var createdAt: Timestamp,
        var updatedAt: Timestamp?
) {
    // Construtor sem argumentos
    constructor() : this(0, "", "", "", "", "", null, null, 0, Timestamp(System.currentTimeMillis()), null)
}
