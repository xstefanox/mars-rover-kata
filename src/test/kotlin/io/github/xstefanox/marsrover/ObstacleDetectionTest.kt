package io.github.xstefanox.marsrover

import io.github.xstefanox.marsrover.Command.Movement
import io.github.xstefanox.marsrover.Command.Movement.Backwards
import io.github.xstefanox.marsrover.Command.Movement.Forward
import io.github.xstefanox.marsrover.Direction.North
import io.github.xstefanox.marsrover.MarsRover.Failure.ObstacleDetected
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class ObstacleDetectionTest {

    @ParameterizedTest
    @MethodSource("arguments")
    fun `an obstacle should be detected`(
        initialPosition: Position,
        movements: Array<Movement>,
        obstaclePosition: Position,
        expectedPosition: Position,
    ) {
        val marsRover = MarsRover.create(
            x = initialPosition.x,
            y = initialPosition.y,
            direction = North,
            Planet(10u, 10u),
            Obstacles(setOf(obstaclePosition))
        ).shouldBeRight()

        val result = marsRover.execute(*movements)

        assertSoftly {
            result shouldBeLeft ObstacleDetected(obstaclePosition)
            marsRover.position shouldBe expectedPosition
        }
    }

    companion object {

        @JvmStatic
        fun arguments() = listOf(
            arguments(Position(0u, 0u), arrayOf(Forward, Forward), Position(0u, 2u), Position(0u, 1u)),
            arguments(Position(5u, 5u), arrayOf(Backwards, Backwards), Position(5u, 3u), Position(5u, 4u)),
        )
    }
}
