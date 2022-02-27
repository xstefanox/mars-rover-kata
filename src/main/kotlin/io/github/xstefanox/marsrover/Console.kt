package io.github.xstefanox.marsrover

import io.github.xstefanox.marsrover.Command.Movement.Forward

class Console(private val marsRover: MarsRover) {

    fun execute(commands: String): Any {
        val roverCommands = mutableListOf<Command>()
        if (commands.first() == 'F') {
            roverCommands += Forward
        }
        marsRover.execute(*roverCommands.toTypedArray())

        return Done
    }

    object Done
}
