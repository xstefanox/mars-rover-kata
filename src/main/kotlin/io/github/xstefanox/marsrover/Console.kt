package io.github.xstefanox.marsrover

import arrow.core.Either
import arrow.core.ValidatedNel
import arrow.core.computations.either
import arrow.core.invalidNel
import arrow.core.traverseValidated
import arrow.core.validNel
import io.github.xstefanox.marsrover.Command.Movement.Backwards
import io.github.xstefanox.marsrover.Command.Movement.Forward
import io.github.xstefanox.marsrover.Command.Rotation.Left
import io.github.xstefanox.marsrover.Command.Rotation.Right
import io.github.xstefanox.marsrover.Console.Failure.InvalidCommands
import io.github.xstefanox.marsrover.Console.Failure.ObstacleDetected

class Console(private val marsRover: MarsRover) {

    suspend fun execute(commands: String): Either<Failure, Done> = either {

        val roverCommands = parse(commands).bind()

        executeCommands(roverCommands).bind()

        Done
    }

    private fun executeCommands(roverCommands: Array<Command>): Either<ObstacleDetected, MarsRover.Done> {
        return marsRover.execute(*roverCommands).mapLeft {
            when (it) {
                is MarsRover.Failure.ObstacleDetected -> ObstacleDetected(it.position)
            }
        }
    }

    private fun parse(commands: String) = commands
        .toList()
        .traverseValidated(Char::toRoverCommand)
        .map(List<Command>::toTypedArray)
        .mapLeft {
            InvalidCommands(it.map(InvalidCommand::value).toSet())
        }

    object Done

    sealed class Failure {
        data class InvalidCommands(val commands: Set<Char>) : Failure()
        data class ObstacleDetected(val position: Position) : Failure()
    }
}

private data class InvalidCommand(val value: Char)

private fun Char.toRoverCommand(): ValidatedNel<InvalidCommand, Command> = when (this) {
    'F' -> Forward.validNel()
    'B' -> Backwards.validNel()
    'R' -> Right.validNel()
    'L' -> Left.validNel()
    else -> InvalidCommand(this).invalidNel()
}
