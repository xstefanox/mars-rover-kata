package io.github.xstefanox.marsrover

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import io.github.xstefanox.marsrover.Command.Movement
import io.github.xstefanox.marsrover.Command.Movement.Backwards
import io.github.xstefanox.marsrover.Command.Movement.Forward
import io.github.xstefanox.marsrover.Command.Rotation
import io.github.xstefanox.marsrover.Command.Rotation.Left
import io.github.xstefanox.marsrover.Command.Rotation.Right
import io.github.xstefanox.marsrover.Direction.East
import io.github.xstefanox.marsrover.Direction.North
import io.github.xstefanox.marsrover.Direction.South
import io.github.xstefanox.marsrover.Direction.West
import io.github.xstefanox.marsrover.MarsRover.Companion.CreationFailure.InvalidPosition.Abscissa
import io.github.xstefanox.marsrover.MarsRover.Companion.CreationFailure.InvalidPosition.Ordinate
import org.slf4j.LoggerFactory
import java.lang.invoke.MethodHandles

class MarsRover private constructor(x: UInt, y: UInt, direction: Direction = North, private val planet: Planet) {

    private val log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass())

    var position: Position = Position(x, y)
        private set(value) {
            Position(value.x, value.y)
            field = value
        }

    var direction: Direction = direction
        private set

    fun execute(vararg commands: Command): Either<Nothing, Done> {
        commands.forEach { command ->
            when (command) {
                is Movement -> move(command)
                is Rotation -> rotate(command)
            }
        }

        return Done.right()
    }

    private fun move(movement: Movement) {
        log.debug("moving ${movement::class.simpleName}")
        when (movement) {
            Backwards -> moveBackwards()
            Forward -> moveForward()
        }
    }

    private fun rotate(rotation: Rotation) {
        log.debug("rotating ${rotation::class.simpleName}")
        when (rotation) {
            Left -> rotateLeft()
            Right -> rotateRight()
        }
    }

    private fun moveForward() {
        position = when (direction) {
            South -> position.copy(y = wrapUnderflow(position.y, planet.yRange))
            East -> position.copy(x = wrapOverflow(position.x, planet.width))
            West -> position.copy(x = wrapUnderflow(position.x, planet.xRange))
            North -> position.copy(y = wrapOverflow(position.y, planet.height))
        }
    }

    private fun moveBackwards() {
        position = when (direction) {
            South -> position.copy(y = wrapOverflow(position.y, planet.height))
            East -> position.copy(x = wrapUnderflow(position.x, planet.xRange))
            West -> position.copy(x = wrapOverflow(position.x, planet.width))
            North -> position.copy(y = wrapUnderflow(position.y, planet.yRange))
        }
    }

    private fun wrapOverflow(axis: UInt, size: UInt) = (axis + Movement.size) % size

    private fun wrapUnderflow(axis: UInt, range: UIntRange) = if (axis == range.first) {
        range.last
    } else {
        axis - Movement.size
    }

    private fun rotateRight() {
        direction = when (direction) {
            North -> East
            South -> West
            West -> North
            East -> South
        }
    }

    private fun rotateLeft() {
        direction = when (direction) {
            North -> West
            South -> East
            West -> South
            East -> North
        }
    }

    object Done

    companion object {

        fun create(
            x: UInt = 0u,
            y: UInt = 0u,
            direction: Direction = North,
            planet: Planet = Planet(1u, 1u)
        ): Either<CreationFailure, MarsRover> {

            if (x >= planet.width) {
                return Abscissa.left()
            }

            if (y >= planet.height) {
                return Ordinate.left()
            }

            return MarsRover(x, y, direction, planet).right()
        }

        sealed class CreationFailure {
            sealed class InvalidPosition : CreationFailure() {
                object Abscissa : InvalidPosition()
                object Ordinate : InvalidPosition()
            }
        }
    }
}
