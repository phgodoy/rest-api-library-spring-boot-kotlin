package library.library.mapper

import org.dozer.DozerBeanMapper
import org.dozer.Mapper

object DozerMapper {
    private val mapper: Mapper = DozerBeanMapper()

    fun <O, D> parseObject(origin: O, destination: Class<D>? ): D{
        return mapper.map(origin, destination)
    }

    fun <O, D> parseListObject(origin: List<O>, destination: Class<D>?): List<D> {
        return origin.map { o -> mapper.map(o, destination) }
    }
}