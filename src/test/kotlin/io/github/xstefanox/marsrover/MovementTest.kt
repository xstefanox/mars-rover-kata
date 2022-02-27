package io.github.xstefanox.marsrover

import io.github.xstefanox.marsrover.Direction.East
import io.github.xstefanox.marsrover.Direction.North
import io.github.xstefanox.marsrover.Direction.South
import io.github.xstefanox.marsrover.Direction.West
import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class MovementTest {

    @ParameterizedTest
    @MethodSource("movements")
    fun `forward movement`(initialDirection: Direction, expectedPosition: Position) {
        val marsRover = MarsRover(0, 0, initialDirection)

        marsRover.moveForward()

        marsRover.position shouldBe expectedPosition
    }

    companion object {

        @JvmStatic
        fun movements(): List<Arguments> = listOf(
            arguments(North, Position(0, 1)),
            arguments(South, Position(0, -1)),
            arguments(East, Position(1, 0)),
            arguments(West, Position(-1, 0)),
        )
    }
}
