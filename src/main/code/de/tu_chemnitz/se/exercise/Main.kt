package de.tu_chemnitz.se.exercise

import de.tu_chemnitz.se.exercise.core.graphics.MainApp
import tornadofx.launch

class Main(
  private val args: Array<String>
) : Runnable {
  companion object {
    @JvmStatic
    fun main(args: Array<String>) {
      launch<MainApp>(args)
      //val app = Main(args)
      //app.run()
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
