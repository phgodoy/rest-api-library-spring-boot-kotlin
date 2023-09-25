import library.library.mocks.MockUser
import library.library.user.User
import library.library.data.vo.v1.UserVO
import library.library.mapper.DozerMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.sql.Timestamp
import kotlin.test.assertEquals

class DozerMapperTest {

    private var inputObject: MockUser? = null

    @BeforeEach
    fun setUp() {
        inputObject = MockUser()
    }

    @Test
    fun parseEntityToVOTest() {
        val output: UserVO = DozerMapper.parseObject(inputObject!!.mockEntity(), UserVO::class.java)
        assertEquals(0, output.id)
        assertEquals("Name Test0", output.name)
        assertEquals("email0@example.com", output.email)
        assertEquals(0, output.addres_id)
        assertEquals("123-456-7890", output.phone)
        assertEquals("Code Test0", output.identificationCode)
        assertEquals(Timestamp(System.currentTimeMillis()), output.createdAt)
        assertEquals(Timestamp(System.currentTimeMillis()), output.updatedAt)
    }

    @Test
    fun parseEntityListToVOListTest() {
        val outputList: List<UserVO> = DozerMapper.parseListObject(inputObject!!.mockEntityList(), UserVO::class.java)

        val outputZero: UserVO = outputList[0]
        assertEquals(0, outputZero.id)
        assertEquals("Name Test0", outputZero.name)
        assertEquals("email0@example.com", outputZero.email)
        assertEquals(0, outputZero.addres_id)
        assertEquals("123-456-7890", outputZero.phone)
        assertEquals("Code Test0", outputZero.identificationCode)
        assertEquals(Timestamp(System.currentTimeMillis()), outputZero.createdAt)
        assertEquals(Timestamp(System.currentTimeMillis()), outputZero.updatedAt)

        val outputSeven: UserVO = outputList[7]
        assertEquals(7, outputSeven.id)
        assertEquals("Name Test7", outputSeven.name)
        assertEquals("email7@example.com", outputSeven.email)
        assertEquals(7, outputSeven.addres_id)
        assertEquals("123-456-7890", outputSeven.phone)
        assertEquals("Code Test7", outputSeven.identificationCode)
        assertEquals(Timestamp(System.currentTimeMillis()), outputSeven.createdAt)
        assertEquals(Timestamp(System.currentTimeMillis() + 7), outputSeven.updatedAt)

        val outputTwelve: UserVO = outputList[12]
        assertEquals(12, outputTwelve.id)
        assertEquals("Name Test12", outputTwelve.name)
        assertEquals("email12@example.com", outputTwelve.email)
        assertEquals(12, outputTwelve.addres_id)
        assertEquals("123-456-7890", outputTwelve.phone)
        assertEquals("Code Test12", outputTwelve.identificationCode)
        assertEquals(Timestamp(System.currentTimeMillis()), outputTwelve.createdAt)
        assertEquals(Timestamp(System.currentTimeMillis() + 12), outputTwelve.updatedAt)
    }

    @Test
    fun parseVOToEntityTest() {
        val output: User = DozerMapper.parseObject(inputObject!!.mockVO(), User::class.java)

        assertEquals(0, output.id)
        assertEquals("Name Test0", output.name)
        assertEquals("email0@example.com", output.email)
        assertEquals(0, output.addres_id)
        assertEquals("123-456-7890", output.phone)
        assertEquals("Code Test0", output.identificationCode)
        assertEquals(Timestamp(System.currentTimeMillis()), output.createdAt)
        assertEquals(Timestamp(System.currentTimeMillis()), output.updatedAt)
    }

    @Test
    fun parseVOListToEntityListTest() {
        val outputList: List<User> = DozerMapper.parseListObject(inputObject!!.mockVOList(), User::class.java)

        val outputZero: User = outputList[0]
        assertEquals(0, outputZero.id)
        assertEquals("Name Test0", outputZero.name)
        assertEquals("email0@example.com", outputZero.email)
        assertEquals(0, outputZero.addres_id)
        assertEquals("123-456-7890", outputZero.phone)
        assertEquals("Code Test0", outputZero.identificationCode)
        assertEquals(Timestamp(System.currentTimeMillis()), outputZero.createdAt)
        assertEquals(Timestamp(System.currentTimeMillis()), outputZero.updatedAt)

        val outputSeven: User = outputList[7]
        assertEquals(7, outputSeven.id)
        assertEquals("Name Test7", outputSeven.name)
        assertEquals("email7@example.com", outputSeven.email)
        assertEquals(7, outputSeven.addres_id)
        assertEquals("123-456-7890", outputSeven.phone)
        assertEquals("Code Test7", outputSeven.identificationCode)
        assertEquals(Timestamp(System.currentTimeMillis()), outputSeven.createdAt)
        assertEquals(Timestamp(System.currentTimeMillis() + 7), outputSeven.updatedAt)

        val outputTwelve: User = outputList[12]
        assertEquals(12, outputTwelve.id)
        assertEquals("Name Test12", outputTwelve.name)
        assertEquals("email12@example.com", outputTwelve.email)
        assertEquals(12, outputTwelve.addres_id)
        assertEquals("123-456-7890", outputTwelve.phone)
        assertEquals("Code Test12", outputTwelve.identificationCode)
        assertEquals(Timestamp(System.currentTimeMillis()), outputTwelve.createdAt)
        assertEquals(Timestamp(System.currentTimeMillis() + 12), outputTwelve.updatedAt)
    }
}
