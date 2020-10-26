package de.tu_chemnitz.se.exercise

interface OutputDevice {
  fun print(message: String)

  @JvmDefault
  fun printLine(message: String) = print("$message\n")
}
