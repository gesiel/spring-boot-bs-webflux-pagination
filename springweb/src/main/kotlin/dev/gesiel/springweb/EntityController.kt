package dev.gesiel.springweb

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/entity")
class EntityController {

    @Autowired
    lateinit var entityRepository: EntityRepository

    @GetMapping
    fun list(
        @PageableDefault(page = 0, size = 20)
        pageable: Pageable
    ) = entityRepository.findAll(pageable)

}