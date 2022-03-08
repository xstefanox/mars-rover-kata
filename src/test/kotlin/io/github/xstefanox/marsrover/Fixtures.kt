package io.github.xstefanox.marsrover

import io.github.xstefanox.marsrover.Command.Movement.Backwards
import io.github.xstefanox.marsrover.Command.Movement.Forward
import io.github.xstefanox.marsrover.Command.Rotation.Left
import io.github.xstefanox.marsrover.Command.Rotation.Right
import kotlin.random.Random
import kotlin.random.nextUInt

internal fun aPosition() = Position(Random.nextUInt(), Random.nextUInt())

internal fun aRotation() = setOf(Left, Right).random()

internal fun aMovement() = setOf(Forward, Backwards).random()
