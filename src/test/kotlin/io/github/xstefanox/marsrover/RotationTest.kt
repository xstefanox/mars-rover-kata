package io.github.xstefanox.marsrover

import io.github.xstefanox.marsrover.Direction.*
import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class RotationTest {

    @ParameterizedTest
    @MethodSource("right rotations")
    fun `right rotation`(initialDirection: Direction, expectedDirection: Direction) {
        val marsRover = MarsRover(0, 0, initialDirection)

        marsRover.rotateRight()

        marsRover.direction shouldBe expectedDirection
    }

    @ParameterizedTest
    @MethodSource("left rotations")
    fun `left rotation`(initialDirection: Direction, expectedDirection: Direction) {
        val marsRover = MarsRover(0, 0, initialDirection)

        marsRover.rotateLeft()

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
