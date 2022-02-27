package io.github.xstefanox.marsrover

import io.github.xstefanox.marsrover.Command.Movement.Backwards
import io.github.xstefanox.marsrover.Command.Movement.Forward
import io.github.xstefanox.marsrover.Command.Rotation.Left
import io.github.xstefanox.marsrover.Command.Rotation.Right

class Console(private val marsRover: MarsRover) {

    fun execute(commands: String): Any {
        val roverCommands = mutableListOf<Command>()
        when (commands.first()) {
            'F' -> roverCommands += Forward
            'B' -> roverCommands += Backwards
            'R' -> roverCommands += Right
            'L' -> roverCommands += Left
        }
        marsRover.execute(*roverCommands.toTypedArray())

        return Done
    }

    object Done
}
