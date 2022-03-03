package io.github.xstefanox.marsrover

import io.github.xstefanox.marsrover.Command.Movement.Backwards
import io.github.xstefanox.marsrover.Command.Movement.Forward
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

class MovementTest {

    @ParameterizedTest
    @MethodSource("forwardMovementsAndExpectedPositions")
    fun `forward movement`(initialDirection: Direction, expectedPosition: Position) {
        val marsRover = MarsRover.create(1u, 1u, initialDirection, Planet(3u, 3u)).get()

        marsRover.move(Forward)

        marsRover.position shouldBe expectedPosition
    }

    @ParameterizedTest
    @MethodSource("backwardsMovementsAndExpectedPositions")
    fun `backwards movement`(initialDirection: Direction, expectedPosition: Position) {
        val marsRover = MarsRover.create(1u, 1u, initialDirection, Planet(3u, 3u)).get()

        marsRover.move(Backwards)

        marsRover.position shouldBe expectedPosition
    }

    companion object {

        @JvmStatic
        fun forwardMovementsAndExpectedPositions(): List<Arguments> = listOf(
            arguments(North, Position(1u, 2u)),
            arguments(South, Position(1u, 0u)),
            arguments(East, Position(2u, 1u)),
            arguments(West, Position(0u, 1u)),
        )

        @JvmStatic
        fun backwardsMovementsAndExpectedPositions(): List<Arguments> = listOf(
            arguments(North, Position(1u, 0u)),
            arguments(South, Position(1u, 2u)),
            arguments(East, Position(0u, 1u)),
            arguments(West, Position(2u, 1u)),
        )
    }
}
