package io.github.xstefanox.marsrover

import io.github.xstefanox.marsrover.Command.Movement.Backwards
import io.github.xstefanox.marsrover.Command.Movement.Forward
import io.github.xstefanox.marsrover.Command.Rotation.Left
import io.github.xstefanox.marsrover.Command.Rotation.Right
import io.github.xstefanox.marsrover.Direction.North
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test

class MultipleCommandTest {

    @Test
    fun `empty list of commands`() {
        val marsRover = MarsRover(0, 0, North)

        marsRover.execute()

        assertSoftly(marsRover) {
            position shouldBe Position(0, 0)
            direction shouldBe North
        }
    }

    @Test
    fun `single command - movement`() {
        val marsRover = MarsRover(0, 0, North)

        marsRover.execute(aMovement())

        marsRover.position shouldNotBe Position(0, 0)
    }

    @Test
    fun `single command - rotation`() {
        val marsRover = MarsRover(0, 0, North)

        marsRover.execute(aRotation())

        marsRover.direction shouldNotBe North
    }

    @Test
    fun `mixed list of commands`() {
        val marsRover = MarsRover(0, 0, North)

        marsRover.execute(aRotation(), aMovement())

        assertSoftly(marsRover) {
            marsRover.position shouldNotBe Position(0, 0)
            marsRover.direction shouldNotBe North
        }
    }
}

private fun aRotation() = setOf(Left, Right).random()

private fun aMovement() = setOf(Forward, Backwards).random()
