package dev.gesiel.springwebflux

import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@Configuration
@EnableReactiveMongoRepositories(
    repositoryBaseClass = BaseCustomReactivePagingRepository::class
)
class MongoConfiguration
