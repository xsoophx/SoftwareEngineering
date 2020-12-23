package de.tuchemnitz.se.exercise.core.configmanager

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import com.mongodb.client.FindIterable
import de.tuchemnitz.se.exercise.DummyData
import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import de.tuchemnitz.se.exercise.persist.configs.EyeTrackingConfig
import de.tuchemnitz.se.exercise.persist.configs.collections.CodeChartsConfigCollection
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.bson.BsonDocument
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Stream

class ConfigManagerTest {

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
        DummyData.manager.checkDBSimilarity()
    }

    @Test
    fun `writing to invalid path does not work`() {
        val testPath = Path.of("bull/shit.txt")

        assertDoesNotThrow {
            DummyData.manager.writeFile(testPath)
        }
    }

    @Test
    fun `reading from invalid path does not work`() {
        val testPath = Path.of("bull/shit.txt")

        assertDoesNotThrow {
            assertThat(DummyData.manager.readFile(testPath)).isNull()
        }
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
        DummyData.manager.configFilePath = "abc"
        assertThat(DummyData.manager.configFilePath).isEqualTo("abc")
    }

    @Test
    fun `invoking of saving functions of config should work`() {
    }

    @BeforeEach
    fun setup() {
        DummyData.codeChartsConfigs.forEach {
            DummyData.codeChartsConfigCollection.saveOne(it)
        }

        DummyData.zoomMapsConfigs().forEach {
            DummyData.zoomMapsConfigCollection.saveOne(it)
        }
    }

    @AfterEach
    fun tearDown() {
        DummyData.codeChartsConfigCollection.deleteMany()
        DummyData.zoomMapsConfigCollection.deleteMany()
    }

    @Test
    fun `assembling all database configs should work`() { // integration test
        val expected = ToolConfigs(
            codeChartsConfig = mostRecentCodeChartsConfig,
            zoomMapsConfig = mostRecentZoomMapsConfig,
            // TODO
            eyeTrackingConfig = EyeTrackingConfig(dummyVal = ""),
            // TODO
            bubbleViewConfig = BubbleViewConfig(
                filter = setOf(
                    FilterInformation(
                        path = "", filter = Filter(
                            gradient = 1, type = "gaussianBlur"
                        )
                    )
                )
            )
        )
        val actual = DummyData.manager.assembleAllConfigurations()
            .copy(
                eyeTrackingConfig = expected.eyeTrackingConfig,
                bubbleViewConfig = expected.bubbleViewConfig
            )
        assertThat(actual).isEqualTo(expected)
    }

    private val mostRecentCodeChartsConfig =
        DummyData.codeChartsConfigs.maxByOrNull { it.savedAt }

    private val mostRecentZoomMapsConfig =
        DummyData.zoomMapsConfigs.maxByOrNull { it.savedAt }
}
