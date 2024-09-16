package dev.gesiel.springwebflux

import java.io.Serializable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.repository.query.MongoEntityInformation
import org.springframework.data.mongodb.repository.support.SimpleReactiveMongoRepository
import org.springframework.data.support.PageableExecutionUtils
import reactor.core.publisher.Mono

class BaseCustomReactivePagingRepository<T, ID : Serializable>(
    private val entityInformation: MongoEntityInformation<T, ID>,
    private val mongoOperations: ReactiveMongoOperations
) : CustomReactivePagingRepository<T, ID>,
    SimpleReactiveMongoRepository<T, ID>(entityInformation, mongoOperations) {

    override fun findAll(pageable: Pageable): Mono<Page<T>> {
        return mongoOperations.count(Query(), entityInformation.collectionName)
            .flatMap { count ->
                mongoOperations
                    .find(
                        Query().with(pageable),
                        entityInformation.javaType,
                        entityInformation.collectionName)
                    .collectList()
                    .map {
                        PageableExecutionUtils.getPage(it, pageable) {
                            count
                        }
                    }
            }
    }
}