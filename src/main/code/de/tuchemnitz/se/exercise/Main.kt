package de.tuchemnitz.se.exercise

import de.tuchemnitz.se.exercise.dataanalyzer.DataClientLaunch
import tornadofx.launch

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch<DataClientLaunch>(args)
        }
    }
}
