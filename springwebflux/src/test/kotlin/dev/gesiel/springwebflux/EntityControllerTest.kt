package dev.gesiel.springwebflux

import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.data.domain.Pageable
import org.springframework.data.web.ReactivePageableHandlerMethodArgumentResolver
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux

class EntityControllerTest {

    private lateinit var entityRepository: EntityRepository
    private lateinit var webTestClient: WebTestClient
    private lateinit var controller: EntityController

    @BeforeEach
    fun setup() {
        entityRepository = mockk()
        controller = EntityController(entityRepository)
        webTestClient = WebTestClient
            .bindToController(controller)
            .argumentResolvers {
                it.addCustomResolver(ReactivePageableHandlerMethodArgumentResolver())
            }
            .build()
    }

    @Test
    fun `testing pagination parameters parse`() = runBlocking {
        val pageableSlot = slot<Pageable>()
        coEvery { entityRepository.findAll(any<Pageable>()) } returns Flux.empty()

        webTestClient
            .get()
            .uri("/entity?page=10&size=100")
            .exchange()
            .expectStatus()
            .isOk

        verify {
            entityRepository.findAll(capture(pageableSlot))
        }

        assertEquals(10, pageableSlot.captured.pageNumber)
        assertEquals(100, pageableSlot.captured.pageSize)
    }

}