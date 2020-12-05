package de.tu_chemnitz.se.exercise

class Main(
    private val args: Array<String>
) : Runnable {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val app = Main(args)
            app.run()
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
