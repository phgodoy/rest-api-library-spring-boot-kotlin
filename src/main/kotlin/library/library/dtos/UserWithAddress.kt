package library.library.dtos

import library.library.address.Address
import library.library.user.User

data class UserWithAddress(
        val user: User,
        val address: Address
)
