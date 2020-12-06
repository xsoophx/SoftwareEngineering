package de.tuchemnitz.se.exercise

class Greeter(
    private val out: OutputDevice
) {
    fun greet(whom: String = "world") {
        out.printLine("Hello, $whom!")
    }
}
