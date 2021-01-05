package de.tuchemnitz.se.exercise.core.configmanager

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.tuchemnitz.se.exercise.DummyData
import de.tuchemnitz.se.exercise.TEST_PATH_CONFIG_FILE
import de.tuchemnitz.se.exercise.persist.configs.EyeTrackingConfig
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

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
            codeChartsConfig = DummyData.codeChartsConfigs.first(),
            eyeTrackingConfig = EyeTrackingConfig(dummyVal = ""),
            dataClientConfig = DataClientConfig(
                colorSampleBoard = setOf(ColorSampleBoard(red = 1, green = 2, blue = 3)),
                exportPath = "exportPath"
            ),
            database = DatabaseConfig(dataBaseName = "test", dataBasePath = "databasePath", username = "root")
        )
    }

    @Test
    fun `encoding valid config file works`() = try {
        val content = assertDoesNotThrow {
            configManager.writeFile()
            configManager.readFile()
        }
        assertThat(Json { prettyPrint = true }.encodeToString(ConfigFile.serializer(), configFile)).isEqualTo(content)
    } finally {
        Files.deleteIfExists(Path.of(TEST_PATH_CONFIG_FILE))
        configManager.writeFile(Paths.get(TEST_PATH_CONFIG_FILE))
    }

    @Test
    fun `decoding valid config file works`() {
        configManager.writeFile(Path.of(TEST_PATH_CONFIG_FILE))
        val expected = configFile
        val actual = configManager.decodeConfig()?.copy(
            eyeTrackingConfig = configFile.eyeTrackingConfig,
            bubbleViewConfig = configFile.bubbleViewConfig
        )
        assertThat(actual).isEqualTo(expected)

        Files.deleteIfExists(Path.of(TEST_PATH_CONFIG_FILE))
    }
}
