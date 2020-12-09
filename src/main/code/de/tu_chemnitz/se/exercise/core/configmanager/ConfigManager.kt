package de.tu_chemnitz.se.exercise.core.configmanager

import de.tu_chemnitz.se.exercise.persist.collections.CodeChartsConfigCollection
import de.tu_chemnitz.se.exercise.persist.configs.CodeChartsConfig
import org.litote.kmongo.KMongo
import org.litote.kmongo.eq
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path

class ConfigManager {
    var configFilePath: String = ""

    private val client = KMongo.createClient() // get com.mongodb.MongoClient new instance
    private val database = client.getDatabase("test") // normal java driver usage
    private val collection = CodeChartsConfigCollection(database)

    companion object {
        val logger: Logger = LoggerFactory.getLogger(ConfigManager::class.java)
    }

    fun checkDBSimilarity(): Boolean {
        return true
    }

    fun writeFile(path: Path, content: String) {
        try {
            Files.writeString(path, content, StandardCharsets.UTF_8)
        } catch (e: IOException) {
            println("Error found: File does not exist")
        }
    }

    fun readFile(path: Path): String {
        return try {
            Files.readString(path, StandardCharsets.UTF_8)
        } catch (e: Exception) {
            when (e) {
                is IOException -> logger.error("Could not read", e)
                is AccessDeniedException -> logger.error("Access denied.", e)
            }
            return ""
        }
    }

    fun assembleAllDatabases(): String {
        val placeholder = collection.find(CodeChartsConfig::matrixViewTime eq 4)

        return ""
    }

    fun getConfig(id: Int) {
    }

    fun saveConfig() {
    }

    fun setConfigPath(path: String) {
        configFilePath = path
    }
}
