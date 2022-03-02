package io.github.xstefanox.marsrover

import io.github.xstefanox.marsrover.MarsRover.Companion.CreationFailure.InvalidPosition
import io.kotest.assertions.arrow.core.shouldBeLeft
import org.junit.jupiter.api.Test

class PlanetTest {

    @Test
    fun `initial position invalid abscissa`() {

        val result = MarsRover.create(x = 10u, planet = Planet.create(width = 10u))

        result shouldBeLeft InvalidPosition.Abscissa
    }

    @Test
    fun `initial position invalid ordinate`() {

        val result = MarsRover.create(y = 10u, planet = Planet.create(height = 10u))
        result shouldBeLeft InvalidPosition.Ordinate
    }
}
