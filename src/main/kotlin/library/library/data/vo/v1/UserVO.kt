package library.library.data.vo.v1

import java.sql.Timestamp

data class UserVO (
        var id: Long = 0,
        var name: String,
        var email: String,
        var addres_id: Long,
        var phone: String,
        var identificationCode: String,
        var createdAt: Timestamp,
        var updatedAt: Timestamp?
)