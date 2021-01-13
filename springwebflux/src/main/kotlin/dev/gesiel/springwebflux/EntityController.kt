package dev.gesiel.springwebflux

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.data.web.ReactivePageableHandlerMethodArgumentResolver
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/entity")
class EntityController(
    private val entityRepository: EntityRepository
){
    @GetMapping
    fun list(
        @PageableDefault(page = 0, size = 20)
        pageable: Pageable
    ) = entityRepository.findAll(pageable)

}