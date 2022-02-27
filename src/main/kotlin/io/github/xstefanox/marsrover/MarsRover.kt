package io.github.xstefanox.marsrover

import io.github.xstefanox.marsrover.Direction.N

class MarsRover(x: Int = 0, y: Int = 0, direction: Direction = N) {

    val position = Position(x, y)

    val direction: Direction = direction
}
