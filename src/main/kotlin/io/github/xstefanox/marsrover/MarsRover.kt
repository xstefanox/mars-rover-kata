package io.github.xstefanox.marsrover

import io.github.xstefanox.marsrover.Direction.N

class MarsRover(x: Int = 0, y: Int = 0, val direction: Direction = N) {

    var position: Position = Position(x, y)
        private set(value) {
            Position(value.x, value.y)
            field = value
        }

    fun moveForward() {
        position = Position(0, 1)
    }
}
