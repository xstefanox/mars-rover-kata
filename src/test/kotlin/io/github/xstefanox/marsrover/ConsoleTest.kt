package io.github.xstefanox.marsrover

import io.github.xstefanox.marsrover.Command.Movement.Forward
import io.github.xstefanox.marsrover.Console.Done
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class ConsoleTest {

    private val marsRover = mockk<MarsRover> {
        every {
            execute(any())
        } just runs
    }

    @Test
    fun `parse valid movement`() {
        val console = Console(marsRover)

        val result = console.execute("F")

        assertSoftly {
            result shouldBe Done
            verify {
                marsRover.execute(Forward)
            }
        }
    }
}
