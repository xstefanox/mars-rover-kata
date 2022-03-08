import io.github.xstefanox.marsrover.Console
import io.github.xstefanox.marsrover.Direction.North
import io.github.xstefanox.marsrover.MarsRover
import io.github.xstefanox.marsrover.Obstacles
import io.github.xstefanox.marsrover.Planet

suspend fun main(args: Array<String>) {
    MarsRover.create(
        0u,
        0u,
        North,
        Planet(10u, 10u),
        Obstacles(emptySet()),
    ).map {
        Console(it)
    }.tap {
        it.execute(args[0])
    }
}
