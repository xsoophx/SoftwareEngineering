package de.tuchemnitz.se.exercise.core.configmanager

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.tuchemnitz.se.exercise.DummyData
import de.tuchemnitz.se.exercise.persist.configs.EyeTrackingConfig
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Stream

class JsonConverterTest {
    companion object {
        private val configManager = DummyData.manager
        private val configFile = ConfigFile(
            general = General(selectionMenuEnabled = true, activatedTool = null, configPath = ""),
            bubbleViewConfig = BubbleViewConfig(
                filter = setOf(
                    FilterInformation(
                        path = "", filter = Filter(gradient = 1, type = "gaussianBlur")
                    )
                )
            ),
            zoomMapsConfig = null,
            codeChartsConfig = null,
            eyeTrackingConfig = EyeTrackingConfig(dummyVal = ""),
            dataClientConfig = DataClientConfig(
                colorSampleBoard = setOf(ColorSampleBoard(red = 1, green = 2, blue = 3)),
                exportPath = "exportPath"
            ),
            database = DatabaseConfig(dataBaseName = "test", dataBasePath = "databasePath", username = "root")
        )

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

    @ParameterizedTest(name = "{index} => Writing/Reading file: {0}")
    @MethodSource("validPaths")
    fun `encoding valid config file works`(validPath: Path) = try {

        val content = assertDoesNotThrow {
            configManager.writeFile(validPath)
            configManager.readFile(validPath)
        }
        assertThat(Json { prettyPrint = true }.encodeToString(ConfigFile.serializer(), configFile)).isEqualTo(content)
    } finally {
        Files.deleteIfExists(validPath)
        configManager.writeFile(Paths.get("cfg.json"))
    }

    @ParameterizedTest(name = "{index} => Writing/Reading file: {0}")
    @MethodSource("validPaths")
    fun `decoding valid config file works`(validPath: Path) = try {
        val content = assertDoesNotThrow {
            configManager.writeFile(validPath)
            configManager.readFile(validPath)
        }
        val actual = content?.let { Json.decodeFromString(ConfigFile.serializer(), it)}?.copy(
            eyeTrackingConfig = configFile.eyeTrackingConfig,
            bubbleViewConfig = configFile.bubbleViewConfig
        )
        assertThat(actual).isEqualTo(configFile)
    } finally {
        Files.deleteIfExists(validPath)
    }
}
