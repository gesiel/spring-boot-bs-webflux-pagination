package dev.gesiel.springweb

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository

interface EntityRepository :
    CrudRepository<Entity, String>,
    PagingAndSortingRepository<Entity, String>