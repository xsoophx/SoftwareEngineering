package de.tu_chemnitz.se.exercise.core.configmanager

import com.mongodb.client.MongoDatabase
import de.tu_chemnitz.se.exercise.persist.collections.AbstractCollection
import de.tu_chemnitz.se.exercise.persist.collections.CodeChartsConfigCollection
import de.tu_chemnitz.se.exercise.persist.configs.CodeChartsConfig
import org.litote.kmongo.KMongo
import org.litote.kmongo.eq
import org.litote.kmongo.getCollection
import java.awt.GraphicsEnvironment
import java.io.File
import java.io.FileNotFoundException

class ConfigManager {
  var configFilePath: String = ""

  private val client = KMongo.createClient() //get com.mongodb.MongoClient new instance
  private val database = client.getDatabase("test") //normal java driver usage
  private val collection = CodeChartsConfigCollection(database)

  fun checkDBSimilarity(): Boolean {
    return true
  }

  fun writeFile(path: String, content: String): Unit {
    if (path === "") return
    try {
      File(path).writeBytes(content.toByteArray())
    } catch (e: FileNotFoundException) {
      println("Error found: File does not exist")
    }
  }

  fun readFile(path: String): String {
    var content = ""
    try {
      content = File(path).readBytes().toString()
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

  fun saveConfig(): Unit {

  }

  fun setConfigPath(path: String) {
    configFilePath = path
  }
}
