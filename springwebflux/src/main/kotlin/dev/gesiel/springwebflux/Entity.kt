package dev.gesiel.springwebflux

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Entity(
    @field:Id
    val id: String,
    val name: String
)