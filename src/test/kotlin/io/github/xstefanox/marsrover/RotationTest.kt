package io.github.xstefanox.marsrover

import io.github.xstefanox.marsrover.Command.Rotation.Left
import io.github.xstefanox.marsrover.Command.Rotation.Right
import io.github.xstefanox.marsrover.Direction.East
import io.github.xstefanox.marsrover.Direction.North
import io.github.xstefanox.marsrover.Direction.South
import io.github.xstefanox.marsrover.Direction.West
import io.github.xstefanox.marsrover.test.get
import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class RotationTest {

    @ParameterizedTest
    @MethodSource("right rotations")
    fun `right rotation`(initialDirection: Direction, expectedDirection: Direction) {
        val marsRover = MarsRover.create(0u, 0u, initialDirection).get()

        marsRover.rotate(Right)

        marsRover.direction shouldBe expectedDirection
    }

    @ParameterizedTest
    @MethodSource("left rotations")
    fun `left rotation`(initialDirection: Direction, expectedDirection: Direction) {
        val marsRover = MarsRover.create(0u, 0u, initialDirection).get()

        marsRover.rotate(Left)

        marsRover.direction shouldBe expectedDirection
    }

    companion object {

        @JvmStatic
        fun `right rotations`(): List<Arguments> = listOf(
            arguments(North, East),
            arguments(East, South),
            arguments(South, West),
            arguments(West, North),
        )

        @JvmStatic
        fun `left rotations`(): List<Arguments> = listOf(
            arguments(North, West),
            arguments(West, South),
            arguments(South, East),
            arguments(East, North),
        )
    }
}
