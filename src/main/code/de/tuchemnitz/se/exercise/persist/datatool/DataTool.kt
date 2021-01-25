package de.tuchemnitz.se.exercise.persist.datatool

import de.tuchemnitz.se.exercise.core.configmanager.ConfigManager
import tornadofx.Controller

class DataTool : Controller() {
    private val configManager: ConfigManager by inject()
    val configs = configManager.getCodeChartsConfig()
}
