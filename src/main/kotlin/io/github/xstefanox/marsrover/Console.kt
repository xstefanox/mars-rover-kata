package io.github.xstefanox.marsrover

import arrow.core.Either
import arrow.core.computations.either
import arrow.core.left
import arrow.core.right
import arrow.core.traverseEither
import io.github.xstefanox.marsrover.Command.Movement.Backwards
import io.github.xstefanox.marsrover.Command.Movement.Forward
import io.github.xstefanox.marsrover.Command.Rotation.Left
import io.github.xstefanox.marsrover.Command.Rotation.Right
import io.github.xstefanox.marsrover.Console.Failure.InvalidCommands

class Console(private val marsRover: MarsRover) {

    suspend fun execute(commands: String): Either<Failure, Done> = either {

        val roverCommands = commands
            .toList()
            .traverseEither(Char::toRoverCommand)
            .map(List<Command>::toTypedArray)
            .bind()

        marsRover.execute(*roverCommands)

        Done
    }

    object Done

    sealed class Failure {
        data class InvalidCommands(val commands: Set<Char>) : Failure()
    }
}

private fun Char.toRoverCommand() = when (this) {
    'F' -> Forward.right()
    'B' -> Backwards.right()
    'R' -> Right.right()
    'L' -> Left.right()
    else -> InvalidCommands(setOf(this)).left()
}
