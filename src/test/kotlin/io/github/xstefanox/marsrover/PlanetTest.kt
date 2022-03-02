package io.github.xstefanox.marsrover

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

class PlanetTest {

    @Test
    fun `initial position abscissa validation`() {
        shouldThrow<IllegalArgumentException> {
            MarsRover(x = 10, planet = Planet.create(width = 10u))
        }
    }

    @Test
    fun `initial position ordinate validation`() {
        shouldThrow<IllegalArgumentException> {
            MarsRover(y = 10, planet = Planet.create(height = 10u))
        }
    }

}
