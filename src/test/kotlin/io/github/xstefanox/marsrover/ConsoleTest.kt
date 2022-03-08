package io.github.xstefanox.marsrover

import arrow.core.left
import arrow.core.right
import io.github.xstefanox.marsrover.Command.Movement
import io.github.xstefanox.marsrover.Command.Movement.Backwards
import io.github.xstefanox.marsrover.Command.Movement.Forward
import io.github.xstefanox.marsrover.Command.Rotation.Left
import io.github.xstefanox.marsrover.Command.Rotation.Right
import io.github.xstefanox.marsrover.Console.Done
import io.github.xstefanox.marsrover.Console.Failure.InvalidCommands
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.assertions.assertSoftly
import io.mockk.called
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

internal class ConsoleTest {

    private val marsRover = mockk<MarsRover> {
        every {
            execute(*anyVararg())
        } answers {
            MarsRover.Done.right()
        }
    }

    private val console = Console(marsRover)

    @ParameterizedTest
    @MethodSource("movements")
    fun `parse valid movement`(movement: String, expectedMovement: Movement) = runTest {

        val result = console.execute(movement)

        assertSoftly {
            result shouldBeRight Done
            verify {
                marsRover.execute(expectedMovement)
            }
        }
    }

    @ParameterizedTest
    @MethodSource("rotations")
    fun `parse valid rotations`(movement: String, expectedRotation: Command.Rotation) = runTest {

        val result = console.execute(movement)

        assertSoftly {
            result shouldBeRight Done
            verify {
                marsRover.execute(expectedRotation)
            }
        }
    }

    @Test
    fun `parse a list of valid commands`() = runTest {

        val result = console.execute("FBLR")

        assertSoftly {
            result shouldBeRight Done
            verify {
                marsRover.execute(Forward, Backwards, Left, Right)
            }
        }
    }

    @Test
    fun `parse an invalid command`() = runTest {
        val result = console.execute("X")

        assertSoftly {
            result shouldBeLeft InvalidCommands(setOf('X'))
            verify {
                marsRover wasNot called
            }
        }
    }

    @Test
    fun `parse a list of invalid commands`() = runTest {
        val result = console.execute("XY")

        assertSoftly {
            result shouldBeLeft InvalidCommands(setOf('X', 'Y'))
            verify {
                marsRover wasNot called
            }
        }
    }

    @Test
    fun `handle obstacle detected`() = runTest {
        val obstaclePosition = aPosition()

        every {
            marsRover.execute(*anyVararg())
        } answers {
            MarsRover.Failure.ObstacleDetected(obstaclePosition).left()
        }

        val result = console.execute("F")

        result shouldBeLeft Console.Failure.ObstacleDetected(obstaclePosition)
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
