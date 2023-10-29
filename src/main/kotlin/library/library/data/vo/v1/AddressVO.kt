package library.library.data.vo.v1

import library.library.user.User
import java.sql.Timestamp

data class AddressVO (
    val user_id: Long = 0,
    val street: String,
    val number: Int,
    val neighbourhood: String,
    val complement: String,
    val postalCode: Int,
    val city: String,
    val state: String,
    val latitude: Double,
    val longitude: Double,
    val status: Int,
    val createdAt: Timestamp,
    val updatedAt: Timestamp?
){
    // Construtor sem argumentos
    constructor() : this(
            0, "", 0, "", "", 0, "", "", 0.0, 0.0, 0, Timestamp(System.currentTimeMillis()), null
    )
}
