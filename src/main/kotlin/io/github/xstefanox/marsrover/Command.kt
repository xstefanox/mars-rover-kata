package io.github.xstefanox.marsrover

sealed class Command {

    sealed class Movement : Command() {
        object Forward : Movement()
        object Backwards : Movement()
    }

    sealed class Rotation : Command() {
        object Right : Rotation()
        object Left : Rotation()
    }
}
