package de.tuchemnitz.se.exercise

import de.tuchemnitz.se.exercise.core.graphics.MainApp
import de.tuchemnitz.se.exercise.core.graphics.codecharts.CCMain
import tornadofx.launch

class Main(
    private val args: Array<String>
) : Runnable {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch<CCMain>(args)
        }
    }

    object Console : OutputDevice {
        override fun print(message: String) {
            kotlin.io.print(message)
        }
    }

    override fun run() {
        val greeter = Greeter(Console)
        greeter.greet()

        for (target in args) {
            greeter.greet(target)
        }
    }
}
