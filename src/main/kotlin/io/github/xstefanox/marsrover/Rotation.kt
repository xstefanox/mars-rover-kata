package io.github.xstefanox.marsrover

sealed class Rotation {
    object Right : Rotation()
    object Left : Rotation()
}
