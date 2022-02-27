package io.github.xstefanox.marsrover

import io.github.xstefanox.marsrover.Command.Movement.Backwards
import io.github.xstefanox.marsrover.Command.Movement.Forward

class Console(private val marsRover: MarsRover) {

    fun execute(commands: String): Any {
        val roverCommands = mutableListOf<Command>()
        if (commands.first() == 'F') {
            roverCommands += Forward
        } else if (commands.first() == 'B') {
            roverCommands += Backwards
        }
        marsRover.execute(*roverCommands.toTypedArray())

        return Done
    }

    object Done
}
