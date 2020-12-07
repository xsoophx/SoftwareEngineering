package de.tu_chemnitz.se.exercise.core.configmanager

import java.awt.GraphicsEnvironment
import java.io.File
import java.io.FileNotFoundException

class ConfigManager {
  var configFilePath: String = ""

  fun checkDBSimilarity(): Boolean {
    return true
  }

  fun writeFile(path: String, content: String): Unit{
    //val file = File(getExternal

    try{
      File(path).writeBytes(content.toByteArray())
    }
    catch(e:FileNotFoundException){
      println("Fehler gefunden: Datei existiert nicht")
    }
  }

  fun readFile(path: String): String {
    try{
      return File(path).readBytes().toString()
    }
    catch(e:FileNotFoundException){
      println("Fehler gefunden: Datei existiert nicht")
      return ""
    }
  }

  fun assembleAllDatabases(): String {
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