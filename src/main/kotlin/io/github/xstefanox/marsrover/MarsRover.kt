package io.github.xstefanox.marsrover

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
import org.slf4j.LoggerFactory
import java.lang.invoke.MethodHandles

class MarsRover(x: Int = 0, y: Int = 0, direction: Direction = North) {

    private val log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass())

    var position: Position = Position(x, y)
        private set(value) {
            Position(value.x, value.y)
            field = value
        }

    var direction: Direction = direction
        private set

    fun move(movement: Movement) {
        log.debug("moving ${movement::class.simpleName}")
        when (movement) {
            Backwards -> moveBackwards()
            Forward -> moveForward()
        }
    }

    fun rotate(rotation: Rotation) {
        log.debug("rotating ${rotation::class.simpleName}")
        when (rotation) {
            Left -> rotateLeft()
            Right -> rotateRight()
        }
    }

    private fun moveForward() {
        position = when (direction) {
            South -> position.copy(y = position.y - 1)
            East -> position.copy(x = position.x + 1)
            West -> position.copy(x = position.x - 1)
            North -> position.copy(y = position.y + 1)
        }
    }

    private fun moveBackwards() {
        position = when (direction) {
            South -> position.copy(y = position.y + 1)
            East -> position.copy(x = position.x - 1)
            West -> position.copy(x = position.x + 1)
            North -> position.copy(y = position.y - 1)
        }
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

    fun execute(vararg commands: Command) {
        if (commands.isNotEmpty()) {
            when (commands.first()) {
                Backwards -> moveBackwards()
                Forward -> moveForward()
                Left -> rotateLeft()
                Right -> rotateRight()
            }
        }
    }
}
