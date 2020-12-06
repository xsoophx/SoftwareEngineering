package de.tuchemnitz.se.exercise

class Greeter(
    private val out: _root_ide_package_.de.tuchemnitz.se.exercise.OutputDevice
) {
    fun greet(whom: String = "world") {
        out.printLine("Hello, $whom!")
    }
}
