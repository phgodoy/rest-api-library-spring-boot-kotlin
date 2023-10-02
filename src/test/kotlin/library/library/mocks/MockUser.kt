package library.library.mocks

import library.library.data.vo.v1.UserVO
import library.library.user.User
import java.sql.Timestamp
import java.util.ArrayList

class MockUser {
    fun <T> mockEntity(number: Int, entityClass: Class<T>): T {
        return when (entityClass) {
            User::class.java -> mockUserEntity(number) as T
            UserVO::class.java -> mockUserVO(number) as T
            else -> throw IllegalArgumentException("Unsupported entity class: $entityClass")
        }
    }

    fun <T> mockEntityList(entityClass: Class<T>): List<T> {

        val entities: ArrayList<T> = ArrayList()
        for (i in 0..13) {
            entities.add(mockEntity(i, entityClass))
        }
        return entities
    }

    private fun mockUserEntity(number: Int): User {
        return User(
                id = number.toLong(),
                name = "Name Test$number",
                email = "email$number@example.com",
                addres_id = number.toLong(),
                phone = "123-456-7890",
                identificationCode = "Code Test$number",
                createdAt = Timestamp(System.currentTimeMillis()),
                updatedAt = Timestamp(System.currentTimeMillis() + number)
        )
    }

    private fun mockUserVO(number: Int): UserVO {
        return UserVO(
                id = number.toLong(),
                name = "Name Test$number",
                email = "email$number@example.com",
                addres_id = number.toLong(),
                phone = "123-456-7890",
                identificationCode = "Code Test$number",
                createdAt = Timestamp(System.currentTimeMillis()),
                updatedAt = Timestamp(System.currentTimeMillis() + number)
        )
    }
}
