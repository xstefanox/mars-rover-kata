package io.github.xstefanox.marsrover

import io.github.xstefanox.marsrover.Direction.North
import io.github.xstefanox.marsrover.Movement.Backwards
import io.github.xstefanox.marsrover.Movement.Forward
import io.github.xstefanox.marsrover.Rotation.Left
import io.github.xstefanox.marsrover.Rotation.Right
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class MultipleCommandTest {

    @Test
    fun `empty list of commands`() {
        val marsRover = MarsRover(0, 0, North)

        marsRover.execute(*emptyArray<Movement>())

        assertSoftly(marsRover) {
            position shouldBe Position(0, 0)
            direction shouldBe North
        }
    }

    @ParameterizedTest
    @MethodSource("movements")
    fun `single command - movement`(movement: Movement) {
        val marsRover = MarsRover(0, 0, North)

        marsRover.execute(movement)

        marsRover.position shouldNotBe Position(0, 0)
    }

    @ParameterizedTest
    @MethodSource("rotations")
    fun `single command - rotation`(rotation: Rotation) {
        val marsRover = MarsRover(0, 0, North)

        marsRover.execute(rotation)

        marsRover.direction shouldNotBe North
    }

    companion object {

        @JvmStatic
        fun movements(): List<Arguments> = listOf(
            arguments(Forward),
            arguments(Backwards),
        )

        @JvmStatic
        fun rotations() = listOf(
            arguments(Right),
            arguments(Left),
        )
    }
}