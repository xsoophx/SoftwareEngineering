package de.tuchemnitz.se.exercise.persist.datatool

import de.tuchemnitz.se.exercise.core.configmanager.ConfigFile
import de.tuchemnitz.se.exercise.core.configmanager.ConfigManager
import kotlinx.serialization.json.Json
import tornadofx.Controller
import java.io.File
import java.nio.file.Path

class DataTool : Controller() {
    private val configManager: ConfigManager by inject()
    val configs = configManager.configFile()
    val configFileJson = File("config.json").writeText(configs)

    fun configFileToDB(path: String) {
        val file = File(path)
        val jsonText = file.readText()
        jsonText?.let { Json.decodeFromString(ConfigFile.serializer(), it) }
    }
}
