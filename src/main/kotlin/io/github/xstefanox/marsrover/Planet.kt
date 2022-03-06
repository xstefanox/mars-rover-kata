package io.github.xstefanox.marsrover

data class Planet(val width: UInt, val height: UInt) {

    val xRange = 0u until width

    val yRange = 0u until height

    companion object {
        fun create(width: UInt = 1u, height: UInt = 1u) = Planet(width, height)
    }
}
