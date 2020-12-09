package de.tu_chemnitz.se.exercise.core.configmanager

import de.tu_chemnitz.se.exercise.persist.collections.CodeChartsConfigCollection
import de.tu_chemnitz.se.exercise.persist.configs.CodeChartsConfig
import org.litote.kmongo.KMongo
import org.litote.kmongo.eq
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream

class ConfigManager {
    var configFilePath: String = ""

    private val client = KMongo.createClient() // get com.mongodb.MongoClient new instance
    private val database = client.getDatabase("test") // normal java driver usage
    private val collection = CodeChartsConfigCollection(database)

    fun checkDBSimilarity(): Boolean {
        return true
    }

    fun writeFile(path: String, content: String) {
        if (path.isEmpty())
            try {
                File(path).bufferedWriter().use { out -> out.write(content) }
                // File(path).writeBytes(content.toByteArray())
            } catch (e: FileNotFoundException) {
                println("Error found: File does not exist")
            }
    }

    fun readFile(path: String): String {
        var content = ""
        try {
            val inputStream: InputStream = File(path).inputStream()

            content = inputStream.bufferedReader().use { it.readText() }
            // content = File(path).bufferedReader().toString()
            // content = File(path).readBytes().toString()
        } catch (e: FileNotFoundException) {
            println("Error found: File does not exist")
        } /*finally {
      return content
    }*/
        return content
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
