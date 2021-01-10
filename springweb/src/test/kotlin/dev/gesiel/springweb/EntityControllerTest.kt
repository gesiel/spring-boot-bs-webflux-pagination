package dev.gesiel.springweb

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.`when`
import org.mockito.Mockito.any
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
class EntityControllerTest {

    @MockBean
    private lateinit var entityRepository: EntityRepository

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `testing pagination parameters parse`() {
        val pageableCaptor = ArgumentCaptor.forClass(Pageable::class.java);
        `when`(entityRepository.findAll(any<Pageable>())).thenReturn(Page.empty())

        mockMvc
            .get("/entity?page=10&size=100")
            .andExpect {
                status { isOk() }
            }

        verify(entityRepository).findAll(pageableCaptor.capture())

        val pageable = pageableCaptor.value
        assertEquals(10, pageable.pageNumber)
        assertEquals(100, pageable.pageSize)
    }

}