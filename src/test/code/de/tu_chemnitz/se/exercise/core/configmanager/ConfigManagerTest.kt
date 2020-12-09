package de.tu_chemnitz.se.exercise.core.configmanager

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.litote.kmongo.KMongo

class ConfigManagerTest {
    private val manager = ConfigManager()
    private val client = KMongo.createClient() // get com.mongodb.MongoClient new instance
    private val database = client.getDatabase("test") // normal java driver usage

    companion object {
        val paths = listOf(
            Triple("TestContent1", "C:/Users/sosur/Desktop/file.txt", "TestContent1"),
            Triple("TestContent2", "E:/se/file.txt", "TestContent2"),
            Triple("TestContent3", "", ""),
            Triple("TestContent4", "TestString", "TestContent4")
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
        paths.forEach { (_, path, expected) ->
            assertThat(manager.readFile(path)).isEqualTo(expected)
        }
    }

    @Test
    fun `assembling all database configs should work`() { // read and combine all Tools' config files into single config file
    }

    @Test
    fun `invoking of get function of a config out of db by id should work`() {
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
