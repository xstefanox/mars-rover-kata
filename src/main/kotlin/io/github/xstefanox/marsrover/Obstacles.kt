package io.github.xstefanox.marsrover

data class Obstacles(private val positions: Set<Position>) {

    operator fun contains(position: Position): Boolean {
        return position in positions
    }
}
