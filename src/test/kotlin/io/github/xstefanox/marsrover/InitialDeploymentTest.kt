package io.github.xstefanox.marsrover

import io.github.xstefanox.marsrover.Direction.North
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class InitialDeploymentTest {

    @Test
    fun `default initial position`() {
        val marsRover = MarsRover.create().shouldBeRight()

        marsRover.position shouldBe Position(0u, 0u)
    }

    @Test
    fun `default initial direction`() {
        val marsRover = MarsRover.create().shouldBeRight()

        marsRover.direction shouldBe North
    }

    @Test
    fun `given initial position`() {
        val marsRover = MarsRover.create(1u, 2u, planet = Planet(10u, 10u)).shouldBeRight()

        marsRover.position shouldBe Position(1u, 2u)
    }

    @ParameterizedTest
    @EnumSource(Direction::class)
    fun `given initial direction`(initialDirection: Direction) {
        val marsRover = MarsRover.create(direction = initialDirection).shouldBeRight()

        marsRover.direction shouldBe initialDirection
    }
}
