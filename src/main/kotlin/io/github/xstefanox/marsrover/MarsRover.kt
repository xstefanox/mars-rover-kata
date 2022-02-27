package io.github.xstefanox.marsrover

import io.github.xstefanox.marsrover.Direction.N
import io.github.xstefanox.marsrover.Direction.S

class MarsRover(x: Int = 0, y: Int = 0, val direction: Direction = N) {

    var position: Position = Position(x, y)
        private set(value) {
            Position(value.x, value.y)
            field = value
        }

    fun moveForward() {
        position = if (direction == S) {
            Position(0, position.y - 1)
        } else {
            Position(0, position.y + 1)
        }
    }
}
