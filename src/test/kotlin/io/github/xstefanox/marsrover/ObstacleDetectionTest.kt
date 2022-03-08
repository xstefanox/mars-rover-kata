package io.github.xstefanox.marsrover

import io.github.xstefanox.marsrover.Command.Movement.Forward
import io.github.xstefanox.marsrover.Direction.North
import io.github.xstefanox.marsrover.MarsRover.Failure.ObstacleDetected
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ObstacleDetectionTest {

    @Test
    fun `an obstacle should be detected`() {
        val obstaclePosition = Position(0u, 1u)
        val marsRover = MarsRover.create(
            x = 0u,
            y = 0u,
            direction = North,
            Planet(10u, 10u),
            Obstacles(setOf(obstaclePosition))
        ).shouldBeRight()

        val result = marsRover.execute(Forward)

        assertSoftly {
            result shouldBeLeft ObstacleDetected(obstaclePosition)
            marsRover.position shouldBe Position(0u, 0u)
        }
    }
}
