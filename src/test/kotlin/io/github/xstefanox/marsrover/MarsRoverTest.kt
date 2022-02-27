package io.github.xstefanox.marsrover

import io.github.xstefanox.marsrover.Direction.N
import io.github.xstefanox.marsrover.Direction.S
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class MarsRoverTest {

    @Test
    fun `default initial position`() {
        val marsRover = MarsRover()

        marsRover.position shouldBe Position(0, 0)
    }

    @Test
    fun `default initial direction`() {
        val marsRover = MarsRover()

        marsRover.direction shouldBe N
    }

    @Test
    fun `given initial position`() {
        val marsRover = MarsRover(1, 2)

        marsRover.position shouldBe Position(1, 2)
    }

    @Test
    fun `given initial direction`() {
        val marsRover = MarsRover(direction = S)

        marsRover.direction shouldBe S
    }
}
