package io.github.xstefanox.marsrover.test

import arrow.core.Either
import io.github.xstefanox.marsrover.MarsRover

fun Either<MarsRover.Companion.CreationFailure, MarsRover>.get(): MarsRover {
    return (this as Either.Right<MarsRover>).value
}
