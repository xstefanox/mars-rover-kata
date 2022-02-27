package io.github.xstefanox.marsrover

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class MarsRoverTest {

    @Test
    internal fun `default initial position`() {
        val marsRover = MarsRover()

        marsRover.position shouldBe "0.0"
    }

    @Test
    internal fun `default initial direction`() {
        val marsRover = MarsRover()

        marsRover.direction shouldBe 'N'
    }

    @Test
    internal fun `given initial position`() {
        val marsRover = MarsRover(1, 2)

        marsRover.position shouldBe "1.2"
    }

    @Test
    internal fun `given initial direction`() {
        val marsRover = MarsRover(direction = 'S')

        marsRover.direction shouldBe 'S'
    }
}
