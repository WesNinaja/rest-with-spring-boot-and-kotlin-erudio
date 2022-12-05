package br.com.erudio.service

import br.com.erudio.data.vo.v1.PersonVO
import br.com.erudio.data.vo.v2.PersonVO as PersonVOV2
import br.com.erudio.exception.ResourceNotFoundException
import br.com.erudio.mapper.DozerMapper
import br.com.erudio.mapper.custom.PersonMapper
import br.com.erudio.model.Person
import br.com.erudio.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonService {

    @Autowired
    private lateinit var repository: PersonRepository
    @Autowired
    private lateinit var mapper: PersonMapper

    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findAll(): List<PersonVO> {
        logger.info("Finding all people!")
        val persons = repository.findAll()
        return DozerMapper.parseListObjects(persons, PersonVO::class.java)
    }


    fun findById(id : Long) : PersonVO{
        logger.info("finding one person")
        val person = repository.findById(id)
                .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
        return DozerMapper.parseObject(person, PersonVO::class.java)
    }

    fun create(person : PersonVO) : PersonVO {
        logger.info("Creating one person with name ${person.firstName}!")
        val entity : Person = DozerMapper.parseObject(person, Person::class.java)
        return DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
    }

    fun createV2(person : PersonVOV2) : PersonVOV2 {
        logger.info("Creating one person with name ${person.firstName}!")
        val entity : Person = mapper.mapVoToEntity(person)
        return mapper.mapEntityToVo(entity)
    }

    fun update(person : PersonVO) : PersonVO {
        logger.info("Updating one person with id ${person.id}!")
        val entity = repository.findById(person.id)
                .orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender

        return DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
    }

    fun delete(id: Long) {
        logger.info("Deleting one person with id ${id}!")
        val entity = repository.findById(id)
                .orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        repository.delete(entity)
    }



}