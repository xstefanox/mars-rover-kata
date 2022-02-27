package io.github.xstefanox.marsrover

import io.github.xstefanox.marsrover.Direction.N
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MovementTest {

    @Test
    fun `forward movement`() {
        val marsRover = MarsRover(0, 0, N)

        marsRover.moveForward()

        marsRover.position shouldBe Position(0, 1)
    }
}
