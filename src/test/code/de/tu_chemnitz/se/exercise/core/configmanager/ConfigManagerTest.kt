package de.tu_chemnitz.se.exercise.core.configmanager

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.mongodb.client.FindIterable
import com.mongodb.client.MongoCollection
import de.tu_chemnitz.se.exercise.persist.collections.CodeChartsConfigCollection
import de.tu_chemnitz.se.exercise.persist.configs.CodeChartsConfig
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.bson.BsonDocument
import org.bson.conversions.Bson
import org.junit.jupiter.api.Test
import org.litote.kmongo.KMongo
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class ConfigManagerTest {
  private val manager = ConfigManager()
  private val client = KMongo.createClient() // get com.mongodb.MongoClient new instance
  private val database = client.getDatabase("test") // normal java driver usage

  private val mockedCollection = mockk<MongoCollection<CodeChartsConfig>>()
  private val collection = CodeChartsConfigCollection(database)

  companion object {
    val paths = listOf<Triple<String, Path, String>>(
      Triple("TestContent1", Paths.get("C:/Users/Florian/Desktop/file.txt"), "TestContent1"),
      //Triple("TestContent2", Paths.get("E:/se/file.txt"), "TestContent2"),
      //Triple("TestContent3", Paths.get(""), ""),
      Triple("TestContent4", Paths.get("TestString"), "TestContent4")
    )
  }

  @Test
  fun `comparing db and file content should work`() { // file content empty, file different from DB, file like DB
    manager.checkDBSimilarity()
  }

  @Test
  fun `writing in file should work`() { // test valid path, invalid path, missing path
    paths.forEach { (content, path, _) ->
      assertThat(manager.writeFile(path, content))
    }
  }

  @Test
  fun `reading out of file should work`() {
    paths.forEach { (content, path, _) ->
      Files.writeString(path, content, StandardCharsets.UTF_8)
    }
    paths.forEach { (_, path, expected) ->
      assertThat(manager.readFile(path)).isEqualTo(expected)
    }
  }

  @Test
  fun `assembling all database configs should work`() { // read and combine all Tools' config files into single config file
  }

  @Test
  fun `invoking of get function of a config out of db by id should work`() {
    val cfg = mockk<FindIterable<CodeChartsConfig>>()
    every { mockedCollection.find(any<Bson>()) } returns cfg
    mockedCollection.find(BsonDocument()) shouldBe cfg
    verify { mockedCollection.find(any<Bson>()) }
  }

  @Test
  fun `invoking of save function of a config into the db should work`() {
  }

  @Test
  fun `setting config path should work`() {
    /* paths.forEach {
         manager.setConfigPath(it.value)
         assertThat(manager.configFilePath).isEqualTo(it.value)
     }*/
  }
}
