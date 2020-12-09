package de.tu_chemnitz.se.exercise.core.configmanager

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.litote.kmongo.KMongo
import java.io.FileNotFoundException

class ConfigManagerTest {
  private val manager = ConfigManager()
  private val client = KMongo.createClient() //get com.mongodb.MongoClient new instance
  private val database = client.getDatabase("test") //normal java driver usage

  companion object {
    val paths = mapOf<String, String>(
      "TestContent1" to "C:/Users/Florian/Desktop/file.txt",
      "TestContent2" to "E:/TUCcloud flthu/Uni/WS 20-21 SE/Projekt/ProjektTest/file.txt",
      "TestContent3" to ""
    )
    val pathsinv = mapOf<String, String>(
      "TestContent4" to "TestString"
    )
  }

  @Test
  fun `comparing db and file content should work`() { //file content empty, file different from DB, file like DB
    manager.checkDBSimilarity()
  }

  @Test
  fun `writing in file should work`() { //test valid path, invalid path, missing path
    pathsinv.forEach { (Content, Path) ->
      assertThrows<FileNotFoundException> {
        manager.writeFile(Path, Content)
      }
    }
    paths.forEach { (Content, Path) ->
      manager.writeFile(Path, Content)
    }
  }

  @Test
  fun `reading out of file should work`() {
    pathsinv.forEach { (_, Path) ->
      assertThrows<FileNotFoundException> {
        manager.readFile(Path)
      }
    }
    paths.forEach { (_, Path) ->
      manager.readFile(Path)
    }
  }

  @Test
  fun `assembling all database configs should work`() { //read and combine all Tools' config files into single config file

  }

  @Test
  fun `invoking of get function of a config out of db by id should work`() {

  }

  @Test
  fun `invoking of save function of a config into the db should work`() {

  }

  @Test
  fun `setting config path should work`() {
    paths.forEach {
      manager.setConfigPath(it.value)
      assertThat(manager.configFilePath).isEqualTo(it.value)
    }
  }
}