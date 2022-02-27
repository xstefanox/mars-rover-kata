package io.github.xstefanox.marsrover

import io.github.xstefanox.marsrover.Command.Movement.Backwards
import io.github.xstefanox.marsrover.Command.Movement.Forward
import io.github.xstefanox.marsrover.Command.Rotation.Left
import io.github.xstefanox.marsrover.Command.Rotation.Right

class Console(private val marsRover: MarsRover) {

    fun execute(commands: String): Any {
        val roverCommands = mutableListOf<Command>()
        if (commands.first() == 'F') {
            roverCommands += Forward
        } else if (commands.first() == 'B') {
            roverCommands += Backwards
        } else if (commands.first() == 'R') {
            roverCommands += Right
        } else if (commands.first() == 'L') {
            roverCommands += Left
        }
        marsRover.execute(*roverCommands.toTypedArray())

        return Done
    }

    object Done
}
