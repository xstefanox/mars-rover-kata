package io.github.xstefanox.marsrover

sealed class Movement {
    object Forward : Movement()
    object Backwards : Movement()
}
