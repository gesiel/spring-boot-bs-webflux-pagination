package dev.gesiel.springwebflux

import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.data.mongodb.core.find
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.repository.query.MongoEntityInformation
import org.springframework.data.mongodb.repository.support.SimpleReactiveMongoRepository

open class EntityRepository(
    private val entityInformation: MongoEntityInformation<Entity, String>,
    private val mongoOperations: ReactiveMongoOperations
) : CustomReactivePagingRepository<Entity, String>,
    SimpleReactiveMongoRepository<Entity, String>(entityInformation, mongoOperations) {

    override fun findAll(pageable: Pageable) =
        mongoOperations
            .find<Entity>(Query().with(pageable), entityInformation.collectionName)
}