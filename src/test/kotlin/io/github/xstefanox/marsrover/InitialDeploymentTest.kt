package io.github.xstefanox.marsrover

import io.github.xstefanox.marsrover.Direction.North
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class InitialDeploymentTest {

    @Test
    fun `default initial position`() {
        val marsRover = MarsRover()

        marsRover.position shouldBe Position(0, 0)
    }

    @Test
    fun `default initial direction`() {
        val marsRover = MarsRover()

        marsRover.direction shouldBe North
    }

    @Test
    fun `given initial position`() {
        val marsRover = MarsRover(1, 2, planet = Planet(10u, 10u))

        marsRover.position shouldBe Position(1, 2)
    }

    @ParameterizedTest
    @EnumSource(Direction::class)
    fun `given initial direction`(initialDirection: Direction) {
        val marsRover = MarsRover(direction = initialDirection)

        marsRover.direction shouldBe initialDirection
    }
}
