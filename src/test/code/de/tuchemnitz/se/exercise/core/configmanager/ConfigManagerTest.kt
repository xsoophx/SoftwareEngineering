package de.tuchemnitz.se.exercise.core.configmanager

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import com.mongodb.client.FindIterable
import de.tuchemnitz.se.exercise.DummyData
import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import de.tuchemnitz.se.exercise.persist.configs.collections.CodeChartsConfigCollection
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.bson.BsonDocument
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Stream

class ConfigManagerTest {

    private val mockedCollection = mockk<CodeChartsConfigCollection>()
    private val dummies = DummyData()

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
        dummies.manager.checkDBSimilarity()
    }

    @ParameterizedTest(name = "{index} => Writing/Reading file: {0}")
    @MethodSource("validPaths")
    fun `writing and reading works`(validPath: Path) = try {
        val message = "Hello world!"

        val content = assertDoesNotThrow {
            dummies.manager.writeFile(validPath, message)
            dummies.manager.readFile(validPath)
        }
        assertThat(message).isEqualTo(content)
    } finally {
        Files.deleteIfExists(validPath)
    }

    @Test
    fun `writing to invalid path does not work`() {
        val testPath = Path.of("bull/shit.txt")

        assertDoesNotThrow {
            dummies.manager.writeFile(testPath, "Hello world!")
        }
    }

    @Test
    fun `reading from invalid path does not work`() {
        val testPath = Path.of("bull/shit.txt")

        assertDoesNotThrow {
            assertThat(dummies.manager.readFile(testPath)).isNull()
        }
    }

    @Test
    fun `reading out of file should work`() {
    }

    @Test
    fun `invoking of get function of a config out of db by id should work`() {
    }

    @Test
    fun `invoking of save function of a config into the db should work`() {
        val mockedResult = mockk<FindIterable<CodeChartsConfig>>()
        every { mockedCollection.find(any()) } returns mockedResult
        mockedCollection.find(BsonDocument()) shouldBe mockedResult
        verify { mockedCollection.find(any()) }
    }

    @Test
    fun `setting config path should work`() {
    }

    @Nested
    inner class DataBaseUsage {

        @BeforeEach
        @ParameterizedTest
        @MethodSource("DummyData.companion.codeChartsConfigs")
        fun setup(config: CodeChartsConfig) {
            dummies.codeChartsConfigCollection.saveOne(config)

            dummies.zoomMapsConfigs.forEach {
                dummies.zoomMapsConfigCollection.saveOne(it)
            }
        }

        @AfterEach
        fun tearDown() {
            dummies.codeChartsConfigCollection.deleteMany()
            dummies.zoomMapsConfigCollection.deleteMany()
        }

        private val dummies = DummyData()

        @Test
        fun `assembling all database configs should work`() { // integration test
        }
    }
}
