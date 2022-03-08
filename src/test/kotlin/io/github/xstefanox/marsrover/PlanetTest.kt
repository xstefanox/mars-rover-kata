package io.github.xstefanox.marsrover

import io.github.xstefanox.marsrover.Command.Movement
import io.github.xstefanox.marsrover.Command.Movement.Backwards
import io.github.xstefanox.marsrover.Command.Movement.Forward
import io.github.xstefanox.marsrover.Direction.East
import io.github.xstefanox.marsrover.Direction.North
import io.github.xstefanox.marsrover.Direction.South
import io.github.xstefanox.marsrover.Direction.West
import io.github.xstefanox.marsrover.MarsRover.Companion.CreationFailure.InvalidPosition
import io.github.xstefanox.marsrover.MarsRover.Done
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class PlanetTest {

    @Nested
    inner class InitialPosition {

        @Test
        fun `initial position invalid abscissa`() {

            val result = MarsRover.create(x = 10u, planet = Planet.create(width = 10u))

            result shouldBeLeft InvalidPosition.Abscissa
        }

        @Test
        fun `initial position invalid ordinate`() {

            val result = MarsRover.create(y = 10u, planet = Planet.create(height = 10u))

            result shouldBeLeft InvalidPosition.Ordinate
        }

        @Test
        fun `initial valid position`() {

            val result = MarsRover.create(x = 0u, y = 0u, planet = Planet.create(width = 10u, height = 10u))

            result.shouldBeRight()
        }
    }

    @Nested
    inner class Movements {

        @ParameterizedTest
        @MethodSource("io.github.xstefanox.marsrover.PlanetTest#abscissaPositionsAndMovements")
        fun `abscissa wrapping`(x: UInt, direction: Direction, movement: Movement, expectedX: UInt) {

            val marsRover = MarsRover.create(
                x = x,
                y = 0u,
                direction = direction,
                planet = Planet.create(width = 10u, height = 1u)
            ).shouldBeRight()

            val result = marsRover.execute(movement)

            assertSoftly {
                result shouldBeRight Done
                marsRover.position.x shouldBe expectedX
            }
        }

        @ParameterizedTest
        @MethodSource("io.github.xstefanox.marsrover.PlanetTest#ordinatePositionsAndMovements")
        fun `ordinate wrapping`(y: UInt, direction: Direction, movement: Movement, expectedY: UInt) {

            val marsRover = MarsRover.create(
                x = 0u,
                y = y,
                direction = direction,
                planet = Planet.create(width = 1u, height = 10u)
            ).shouldBeRight()

            val result = marsRover.execute(movement)

            assertSoftly {
                result shouldBeRight Done
                marsRover.position.y shouldBe expectedY
            }
        }
    }

    companion object {

        @JvmStatic
        fun abscissaPositionsAndMovements() = listOf<Arguments>(
            arguments(9, East, Forward, 0),
            arguments(9, West, Backwards, 0),
            arguments(0, West, Forward, 9),
            arguments(0, East, Backwards, 9),
        )

        @JvmStatic
        fun ordinatePositionsAndMovements() = listOf<Arguments>(
            arguments(9, North, Forward, 0),
            arguments(9, South, Backwards, 0),
            arguments(0, South, Forward, 9),
            arguments(0, North, Backwards, 9),
        )
    }
}
