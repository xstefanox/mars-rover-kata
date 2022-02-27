package io.github.xstefanox.marsrover

import io.github.xstefanox.marsrover.Command.Movement.Backwards
import io.github.xstefanox.marsrover.Command.Movement.Forward
import io.github.xstefanox.marsrover.Command.Rotation.Left
import io.github.xstefanox.marsrover.Command.Rotation.Right

class Console(private val marsRover: MarsRover) {

    fun execute(commands: String): Any {

        val roverCommands = commands
            .map(Char::toRoverCommand)
            .toTypedArray()

        marsRover.execute(*roverCommands)

        return Done
    }

    object Done
}

private fun Char.toRoverCommand() = when (this) {
    'F' -> Forward
    'B' -> Backwards
    'R' -> Right
    'L' -> Left
    else -> TODO()
}
