package io.github.xstefanox.marsrover

import io.github.xstefanox.marsrover.Direction.East
import io.github.xstefanox.marsrover.Direction.North
import io.github.xstefanox.marsrover.Direction.South
import io.github.xstefanox.marsrover.Direction.West
import io.github.xstefanox.marsrover.Movement.Backwards
import io.github.xstefanox.marsrover.Movement.Forward

class MarsRover(x: Int = 0, y: Int = 0, val direction: Direction = North) {

    var position: Position = Position(x, y)
        private set(value) {
            Position(value.x, value.y)
            field = value
        }

    fun move(forward: Movement) {
        when (forward) {
            Backwards -> moveBackwards()
            Forward -> moveForward()
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
}
