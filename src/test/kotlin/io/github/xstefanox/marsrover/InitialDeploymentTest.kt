package io.github.xstefanox.marsrover

import io.github.xstefanox.marsrover.Direction.North
import io.github.xstefanox.marsrover.test.get
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class InitialDeploymentTest {

    @Test
    fun `default initial position`() {
        val marsRover = MarsRover.create().get()

        marsRover.position shouldBe Position(0u, 0u)
    }

    @Test
    fun `default initial direction`() {
        val marsRover = MarsRover.create().get()

        marsRover.direction shouldBe North
    }

    @Test
    fun `given initial position`() {
        val marsRover = MarsRover.create(1u, 2u, planet = Planet(10u, 10u)).get()

        marsRover.position shouldBe Position(1u, 2u)
    }

    @ParameterizedTest
    @EnumSource(Direction::class)
    fun `given initial direction`(initialDirection: Direction) {
        val marsRover = MarsRover.create(direction = initialDirection).get()

        marsRover.direction shouldBe initialDirection
    }
}
