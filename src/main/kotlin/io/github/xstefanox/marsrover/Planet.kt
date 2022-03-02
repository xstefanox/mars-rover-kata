package io.github.xstefanox.marsrover

data class Planet(val width: UInt, val height: UInt) {

    companion object {
        fun create(width: UInt = 1u, height: UInt = 1u) = Planet(width, height)
    }
}
