package io.github.xstefanox.marsrover

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class MarsRoverTest {

    @Test
    internal fun `the mars rover should have a default initial position`() {
        val marsRover = MarsRover()

        marsRover.position shouldBe "0.0"
    }
}
