package io.github.xstefanox.marsrover

import io.github.xstefanox.marsrover.Direction.East
import io.github.xstefanox.marsrover.Direction.North
import io.github.xstefanox.marsrover.Direction.South
import io.github.xstefanox.marsrover.Direction.West
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MovementTest {

    @Test
    fun `forward movement to north`() {
        val marsRover = MarsRover(0, 0, North)

        marsRover.moveForward()

        marsRover.position shouldBe Position(0, 1)
    }

    @Test
    fun `forward movement to south`() {
        val marsRover = MarsRover(0, 0, South)

        marsRover.moveForward()

        marsRover.position shouldBe Position(0, -1)
    }

    @Test
    fun `forward movement to east`() {
        val marsRover = MarsRover(0, 0, East)

        marsRover.moveForward()

        marsRover.position shouldBe Position(1, 0)
    }

    @Test
    fun `forward movement to west`() {
        val marsRover = MarsRover(0, 0, West)

        marsRover.moveForward()

        marsRover.position shouldBe Position(-1, 0)
    }
}
