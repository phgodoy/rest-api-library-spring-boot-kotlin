package library.library.mocks

import library.library.data.vo.v1.UserVO
import library.library.user.User
import java.sql.Timestamp
import java.util.ArrayList

class MockUser {
    fun mockEntity(): User {
        return mockEntity(0)
    }

    fun mockVO(): UserVO {
        return mockVO(0)
    }

    fun mockEntityList(): ArrayList<User> {
        val users: ArrayList<User> = ArrayList<User>()
        for (i in 0..13) {
            users.add(mockEntity(i))
        }
        return users
    }

    fun mockVOList(): ArrayList<UserVO> {
        val userVOs: ArrayList<UserVO> = ArrayList()
        for (i in 0..13) {
            userVOs.add(mockVO(i))
        }
        return userVOs
    }

    fun mockEntity(number: Int): User {
        return User(
                id = number.toLong(),
                name = "Name Test$number",
                email = "email$number@example.com",
                addres_id = number.toLong(), // Correção: Alterado de "addres_id" para "address_id"
                phone = "123-456-7890",
                identificationCode = "Code Test$number",
                createdAt = Timestamp(System.currentTimeMillis()),
                updatedAt = Timestamp(System.currentTimeMillis() + number)
        )
    }


    fun mockVO(number: Int): UserVO {
        return UserVO(
                id = number.toLong(),
                name = "Name Test$number",
                email = "email$number@example.com",
                addres_id = number.toLong(), // Correção: Alterado de "addres_id" para "addres_id"
                phone = "123-456-7890", // Use um número de telefone válido
                identificationCode = "Code Test$number",
                createdAt = Timestamp(System.currentTimeMillis()),
                updatedAt = Timestamp(System.currentTimeMillis() + number)
        )
    }
}
