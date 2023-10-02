import library.library.mocks.MockUser
import library.library.user.User
import library.library.data.vo.v1.UserVO
import library.library.mapper.DozerMapper
import org.dozer.DozerBeanMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.sql.Timestamp
import java.time.temporal.ChronoUnit
import java.util.ArrayList
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class DozerMapperTest {

    private var inputObject: MockUser? = null

    @BeforeEach
    fun setUp() {
        inputObject = MockUser()
    }

    @Test
    fun parseEntityToVOTest() {
        val output: UserVO = DozerMapper.parseObject(inputObject!!.mockEntity(0, User::class.java), UserVO::class.java)
        assertEquals(0, output.id)
        assertEquals("Name Test0", output.name)
        assertEquals("email0@example.com", output.email)
        assertEquals(0, output.addres_id)
        assertEquals("123-456-7890", output.phone)
        assertEquals("Code Test0", output.identificationCode)

        val toleranceMillis = 1000
        assertTimestampCloseToNow(output.createdAt, toleranceMillis)
        assertTimestampCloseToNow(output.updatedAt, toleranceMillis)
    }

    @Test
    fun parseEntityListToVOListTest() {
        val entityList: List<User> = inputObject!!.mockEntityList(User::class.java)

        val outputList: List<UserVO> = entityList.map { entity ->
            UserVO(
                    id = entity.id,
                    name = entity.name,
                    email = entity.email,
                    addres_id = entity.addres_id,
                    phone = entity.phone,
                    identificationCode = entity.identificationCode,
                    createdAt = entity.createdAt,
                    updatedAt = entity.updatedAt
            )
        }

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
        assertTimestampCloseToNow(userVO.createdAt)
        assertTimestampCloseToNow(userVO.updatedAt)
    }

    private fun assertTimestampCloseToNow(timestamp: Timestamp?) {
        assertNotNull(timestamp)

        val expectedTime = Timestamp(System.currentTimeMillis()).toLocalDateTime()
        val actualTime = timestamp.toLocalDateTime()

        val toleranceSeconds = 1 // Define uma tolerância de 1 segundo (ajuste conforme necessário)
        val toleranceMillis = 500 // Define uma tolerância de 500 milissegundos (ajuste conforme necessário)

        assertTrue(
                Math.abs(ChronoUnit.SECONDS.between(expectedTime, actualTime)) <= toleranceSeconds &&
                        Math.abs(ChronoUnit.MILLIS.between(expectedTime, actualTime)) <= toleranceMillis
        )
    }

    @Test
    fun parseVOToEntityTest() {

        val dozerMapper = DozerBeanMapper()

        val output: UserVO = dozerMapper.map(inputObject!!.mockEntity(0, User::class.java), UserVO::class.java)

        println("Output User:")
        println("id: ${output.id}")
        println("name: ${output.name}")
        println("email: ${output.email}")
        println("addres_id: ${output.addres_id}")
        println("phone: ${output.phone}")
        println("identificationCode: ${output.identificationCode}")
        println("createdAt: ${output.createdAt}")
        println("updatedAt: ${output.updatedAt}")

        assertEquals(0, output.id)
        assertEquals("Name Test0", output.name)
        assertEquals("email0@example.com", output.email)
        assertEquals(0, output.addres_id)
        assertEquals("123-456-7890", output.phone)
        assertEquals("Code Test0", output.identificationCode)

        val toleranceMillis = 1000
        assertTimestampCloseToNow(output.createdAt, toleranceMillis)
        assertTimestampCloseToNow(output.updatedAt, toleranceMillis)
    }

    private fun assertTimestampCloseToNow(timestamp: Timestamp?, toleranceMillis: Int) {
        assertNotNull(timestamp)

        val expectedTime = Timestamp(System.currentTimeMillis()).toLocalDateTime()
        val actualTime = timestamp.toLocalDateTime()

        assertTrue(
                Math.abs(ChronoUnit.MILLIS.between(expectedTime, actualTime)) <= toleranceMillis
        )
    }
    @Test
    fun parseVOListToEntityListTest() {
        val dozerMapper = DozerBeanMapper()

        val inputEntityList: List<UserVO> = inputObject!!.mockEntityList(UserVO::class.java)
        val outputList: List<UserVO> = inputEntityList.map { user ->
            dozerMapper.map(user, UserVO::class.java)
        }

        val outputZero: UserVO = outputList[0]
        assertEquals(0, outputZero.id)
        assertEquals("Name Test0", outputZero.name)
        assertEquals("email0@example.com", outputZero.email)
        assertEquals(0, outputZero.addres_id)
        assertEquals("123-456-7890", outputZero.phone)
        assertEquals("Code Test0", outputZero.identificationCode)

        val outputSeven: UserVO = outputList[7]
        assertEquals(7, outputSeven.id)
        assertEquals("Name Test7", outputSeven.name)
        assertEquals("email7@example.com", outputSeven.email)
        assertEquals(7, outputSeven.addres_id)
        assertEquals("123-456-7890", outputSeven.phone)
        assertEquals("Code Test7", outputSeven.identificationCode)

        val outputTwelve: UserVO = outputList[12]
        assertEquals(12, outputTwelve.id)
        assertEquals("Name Test12", outputTwelve.name)
        assertEquals("email12@example.com", outputTwelve.email)
        assertEquals(12, outputTwelve.addres_id)
        assertEquals("123-456-7890", outputTwelve.phone)
        assertEquals("Code Test12", outputTwelve.identificationCode)

        // Asserções para as propriedades de data e hora (createdAt e updatedAt)
        val currentTime = System.currentTimeMillis()
        val toleranceMillis = 1000 // 1 segundo de tolerância
        for (userVO in outputList) {
            assertTimestampCloseToNow(userVO.createdAt, currentTime, toleranceMillis)
            assertTimestampCloseToNow(userVO.updatedAt, currentTime, toleranceMillis)
        }
    }


    private fun assertTimestampCloseToNow(timestamp: Timestamp?, currentTime: Long, toleranceMillis: Int) {
        assertNotNull(timestamp)

        val expectedTime = Timestamp(currentTime).toLocalDateTime()
        val actualTime = timestamp.toLocalDateTime()

        assertTrue(
                Math.abs(ChronoUnit.MILLIS.between(expectedTime, actualTime)) <= toleranceMillis
        )
    }
}
