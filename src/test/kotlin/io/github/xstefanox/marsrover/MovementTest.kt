package io.github.xstefanox.marsrover

import io.github.xstefanox.marsrover.Direction.E
import io.github.xstefanox.marsrover.Direction.N
import io.github.xstefanox.marsrover.Direction.S
import io.github.xstefanox.marsrover.Direction.W
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MovementTest {

    @Test
    fun `forward movement to north`() {
        val marsRover = MarsRover(0, 0, N)

        marsRover.moveForward()

        marsRover.position shouldBe Position(0, 1)
    }

    @Test
    fun `forward movement to south`() {
        val marsRover = MarsRover(0, 0, S)

        marsRover.moveForward()

        marsRover.position shouldBe Position(0, -1)
    }

    @Test
    fun `forward movement to east`() {
        val marsRover = MarsRover(0, 0, E)

        marsRover.moveForward()

        marsRover.position shouldBe Position(1, 0)
    }

    @Test
    fun `forward movement to west`() {
        val marsRover = MarsRover(0, 0, W)

        marsRover.moveForward()

        marsRover.position shouldBe Position(-1, 0)
    }
}
