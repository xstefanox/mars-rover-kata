package io.github.xstefanox.marsrover

sealed class Command {

    sealed class Movement : Command() {
        object Forward : Movement()
        object Backwards : Movement()

        companion object {
            val size = 1u
        }
    }

    sealed class Rotation : Command() {
        object Right : Rotation()
        object Left : Rotation()
    }
}
