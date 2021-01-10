package dev.gesiel.springweb

import java.util.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class DatabaseInitializer : ApplicationRunner {

    @Autowired
    private lateinit var entityRepository: EntityRepository

    override fun run(args: ApplicationArguments?) {
        if (entityRepository.count() > 0) return

        repeat(200) {
            entityRepository.save(
                Entity(
                    id = UUID.randomUUID().toString(),
                    name = "Name $it"
                )
            )
        }
    }

}