package de.tu_chemnitz.se.exercise.core.configmanager

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class ConfigManagerTest {
  private val manager = ConfigManager()

  companion object {
    val paths = mapOf<String, String>(
      "TestContent" to "",
      "TestContent" to "TestString",
      "TestContent" to "E:/TUCcloud flthu/Uni/WS 20-21 SE/Projekt/ProjektTest/file.txt"
    )
  }

  @Test
  fun `comparing db and file content should work`() { //file content empty, file different from DB, file like DB
    manager.checkDBSimilarity()
  }

  @Test
  fun `writing in file should work`() { //test valid path, invalid path, missing path
    paths.forEach {
      manager.writeFile(it.value, it.key)
    }
  }

  @Test
  fun `reading out of file should work`() {
    paths.forEach {
      assertThat(manager.readFile(it.value)).isEqualTo(it.key)
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
    paths.forEach{
      manager.setConfigPath(it.value)
      assertThat(manager.configFilePath).isEqualTo(it.value)
    }
  }
}