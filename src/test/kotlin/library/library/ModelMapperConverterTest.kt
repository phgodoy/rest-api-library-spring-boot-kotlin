import library.library.data.vo.v1.UserVO
import library.library.mocks.MockUser
import library.library.user.User
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.modelmapper.ModelMapper
import java.sql.Timestamp
import java.time.temporal.ChronoUnit
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ModelMapperConverterTest {

    private val modelMapper = ModelMapper()

    private lateinit var inputObject: MockUser

    @BeforeEach
    fun setUp() {
        inputObject = MockUser()
    }

    @Test
    fun parseEntityToVOTest() {
        val user = inputObject!!.mockEntity(0, User::class.java)
        val output: UserVO = modelMapper.map(user, UserVO::class.java)

        assertUserVO(output, 0, "Name Test0", "email0@example.com", 0, "123-456-7890", "Code Test0")
    }

    @Test
    fun parseEntityListToVOListTest() {
        val inputEntityList: List<User> = inputObject!!.mockEntityList(User::class.java)
        val outputList: List<UserVO> = inputEntityList.map { modelMapper.map(it, UserVO::class.java) }

        assertUserVO(outputList[0], 0, "Name Test0", "email0@example.com", 0, "123-456-7890", "Code Test0")
        assertUserVO(outputList[7], 7, "Name Test7", "email7@example.com", 7, "123-456-7890", "Code Test7")
        assertUserVO(outputList[12], 12, "Name Test12", "email12@example.com", 12, "123-456-7890", "Code Test12")
    }

    private fun assertUserVO(userVO: UserVO, id: Long, name: String, email: String, addresId: Long, phone: String, identificationCode: String) {
        assertEquals(id, userVO.id)
        assertEquals(name, userVO.name)
        assertEquals(email, userVO.email)
        assertEquals(addresId, userVO.addres_id)
        assertEquals(phone, userVO.phone)
        assertEquals(identificationCode, userVO.identificationCode)
        val toleranceMillis = 1000
        assertTimestampCloseToNow(userVO.createdAt, toleranceMillis)
        assertTimestampCloseToNow(userVO.updatedAt, toleranceMillis)
    }

    private fun assertTimestampCloseToNow(timestamp: Timestamp?, toleranceMillis: Int) {
        assertNotNull(timestamp)

        val expectedTime = Timestamp(System.currentTimeMillis()).toLocalDateTime()
        val actualTime = timestamp.toLocalDateTime()

        val differenceMillis = Math.abs(ChronoUnit.MILLIS.between(expectedTime, actualTime))

        assertTrue(differenceMillis <= toleranceMillis)
    }
}
