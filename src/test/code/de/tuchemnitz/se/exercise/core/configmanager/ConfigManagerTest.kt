package de.tuchemnitz.se.exercise.core.configmanager

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import com.mongodb.client.FindIterable
import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import de.tuchemnitz.se.exercise.persist.configs.collections.CodeChartsConfigCollection
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.bson.BsonDocument
import org.bson.conversions.Bson
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.litote.kmongo.KMongo
import org.litote.kmongo.eq
import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Stream

class ConfigManagerTest {
    private val manager = ConfigManager()
    private val client = KMongo.createClient() // get com.mongodb.MongoClient new instance
    private val database = client.getDatabase("test")
    private val codeChartsConfigCollection = CodeChartsConfigCollection(database)
    private val configs = setOf(
        CodeChartsConfig(
            grid = Pair(100, 200),
            pictureViewTime = 1,
            matrixViewTime = 2
        ),
        CodeChartsConfig(
            grid = Pair(0, 0),
            pictureViewTime = 0,
            matrixViewTime = 0
        ),
        CodeChartsConfig(
            grid = Pair(400, 400),
            pictureViewTime = 4,
            matrixViewTime = 4
        )
    )

    private val mockedCollection = mockk<CodeChartsConfigCollection>()

    companion object {
        @JvmStatic
        @Suppress("unused")
        fun validPaths(): Stream<Path> = Stream.of(
            Path.of("test.txt"),
            Path.of("dummy.cfg"),
            Path.of("./foo.conf"),
            Path.of("../born.json"),
            Files.createTempFile("", ".conf")
        )
    }

    @Test
    fun `comparing db and file content should work`() { // file content empty, file different from DB, file like DB
        manager.checkDBSimilarity()
    }

    @ParameterizedTest(name = "{index} => Writing/Reading file: {0}")
    @MethodSource("validPaths")
    fun `writing and reading works`(validPath: Path) = try {
        val message = "Hello world!"

        val content = assertDoesNotThrow {
            manager.writeFile(validPath, message)
            manager.readFile(validPath)
        }
        assertThat(message).isEqualTo(content)
    } finally {
        Files.deleteIfExists(validPath)
    }

    @Test
    fun `writing to invalid path does not work`() {
        val testPath = Path.of("bull/shit.txt")

        assertDoesNotThrow {
            manager.writeFile(testPath, "Hello world!")
        }
    }

    @Test
    fun `reading from invalid path does not work`() {
        val testPath = Path.of("bull/shit.txt")

        assertDoesNotThrow {
            assertThat(manager.readFile(testPath)).isNull()
        }
    }

    @Test
    fun `reading out of file should work`() {
    }

    @Test
    fun `assembling all database configs should work`() { // integration test
        configs.forEach {
            codeChartsConfigCollection.saveOne(it)
            assertThat(codeChartsConfigCollection.find(CodeChartsConfig::_id eq it._id)).contains(it)
        }
    }

    @Test
    fun `invoking of get function of a config out of db by id should work`() {
    }

    @Test
    fun `invoking of save function of a config into the db should work`() {
        val mockedResult = mockk<FindIterable<CodeChartsConfig>>()
        every { mockedCollection.find(any<Bson>()) } returns mockedResult
        mockedCollection.find(BsonDocument()) shouldBe mockedResult
        verify { mockedCollection.find(any<Bson>()) }
    }

    @Test
    fun `setting config path should work`() {
    }
}
