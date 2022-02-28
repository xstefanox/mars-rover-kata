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
import io.github.xstefanox.marsrover.Console.Result.Failure

class Console(private val marsRover: MarsRover) {

    fun execute(commands: String): Either<Failure, Done> = either.eager { // TODO remove the eager

        val roverCommands = commands
            .toList()
            .traverseEither(Char::toRoverCommand)
            .map(List<Command>::toTypedArray)
            .bind()

        marsRover.execute(*roverCommands)

        Done
    }

    object Done

    sealed class Result {
        object Failure : Result()
    }
}

private fun Char.toRoverCommand() = when (this) {
    'F' -> Forward.right()
    'B' -> Backwards.right()
    'R' -> Right.right()
    'L' -> Left.right()
    else -> Failure.left()
}
