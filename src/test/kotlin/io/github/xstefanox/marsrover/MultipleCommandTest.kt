package io.github.xstefanox.marsrover

import io.github.xstefanox.marsrover.Direction.North
import io.github.xstefanox.marsrover.MarsRover.Done
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test

class MultipleCommandTest {

    @Test
    fun `empty list of commands`() {
        val marsRover = MarsRover.create(0u, 0u, North).shouldBeRight()

        val result = marsRover.execute()

        assertSoftly(marsRover) {
            result shouldBeRight Done
            position shouldBe Position(0u, 0u)
            direction shouldBe North
        }
    }

    @Test
    fun `single command - movement`() {
        val marsRover = MarsRover.create(0u, 0u, North, Planet(10u, 10u)).shouldBeRight()

        val result = marsRover.execute(aMovement())

        assertSoftly {
            result shouldBeRight Done
            marsRover.position shouldNotBe Position(0u, 0u)
        }
    }

    @Test
    fun `single command - rotation`() {
        val marsRover = MarsRover.create(0u, 0u, North).shouldBeRight()

        val result = marsRover.execute(aRotation())

        assertSoftly {
            result shouldBeRight Done
            marsRover.direction shouldNotBe North
        }
    }

    @Test
    fun `mixed list of commands`() {
        val marsRover = MarsRover.create(0u, 0u, North, Planet(10u, 10u)).shouldBeRight()

        val result = marsRover.execute(aRotation(), aMovement())

        assertSoftly(marsRover) {
            result shouldBeRight Done
            marsRover.position shouldNotBe Position(0u, 0u)
            marsRover.direction shouldNotBe North
        }
    }
}
