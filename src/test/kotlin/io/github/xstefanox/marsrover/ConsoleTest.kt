package io.github.xstefanox.marsrover

import io.github.xstefanox.marsrover.Command.Movement
import io.github.xstefanox.marsrover.Command.Movement.Backwards
import io.github.xstefanox.marsrover.Command.Movement.Forward
import io.github.xstefanox.marsrover.Command.Rotation.Left
import io.github.xstefanox.marsrover.Command.Rotation.Right
import io.github.xstefanox.marsrover.Console.Done
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

internal class ConsoleTest {

    private val marsRover = mockk<MarsRover> {
        every {
            execute(any())
        } just runs
    }

    @ParameterizedTest
    @MethodSource("movements")
    fun `parse valid movement`(movement: String, expectedMovement: Movement) {
        val console = Console(marsRover)

        val result = console.execute(movement)

        assertSoftly {
            result shouldBe Done
            verify {
                marsRover.execute(expectedMovement)
            }
        }
    }

    @ParameterizedTest
    @MethodSource("rotations")
    fun `parse valid rotations`(movement: String, expectedRotation: Command.Rotation) {
        val console = Console(marsRover)

        val result = console.execute(movement)

        assertSoftly {
            result shouldBe Done
            verify {
                marsRover.execute(expectedRotation)
            }
        }
    }

    companion object {

        @JvmStatic
        fun movements() = listOf(
            arguments("F", Forward),
            arguments("B", Backwards),
        )

        @JvmStatic
        fun rotations() = listOf(
            arguments("R", Right),
            arguments("L", Left),
        )
    }
}
