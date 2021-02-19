package de.tuchemnitz.se.exercise.core.configmanager

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.tuchemnitz.se.exercise.DummyData
import de.tuchemnitz.se.exercise.TEST_PATH_CONFIG_FILE
import de.tuchemnitz.se.exercise.persist.configs.EyeTrackingConfig
import javafx.scene.input.KeyCode
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.awt.Toolkit
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class JsonConverterTest {
    companion object {
        private val configManager = DummyData.configManager

        private val configFile = ConfigFile(
            general = General(
                selectionMenuEnabled = true,
                activatedTool = null,
                fullscreen = true,
                width = Toolkit.getDefaultToolkit().screenSize.width,
                height = Toolkit.getDefaultToolkit().screenSize.height,
                exportPath = "exportPath",
                masterPath = "masterPath",
                imagePath = "imagePath"
            ),
            bubbleViewConfig = BubbleViewConfig(
                filter = setOf(
                    FilterInformation(
                        name = "", filter = Filter(gradient = 1, type = "gaussianBlur")
                    )
                )
            ),
            zoomMapsConfig = ConfigFileZoomMaps(
                keyBindings = KeyBindings(
                    up = KeyCode.C,
                    down = KeyCode.C,
                    left = KeyCode.C,
                    right = KeyCode.C,
                    inKey = KeyCode.C,
                    out = KeyCode.C
                ),
                pictures = listOf(ZoomInformation(name = "", zoomSpeed = 1.0))
            ),
            codeChartsConfig = DummyData.codeChartsConfigs.first(),
            eyeTrackingConfig = EyeTrackingConfig(pictures = emptyList()),
            dataClientConfig = DataClientConfig(
                colorSampleBoard = setOf(
                    ColorSampleBoard(
                        red = 50,
                        green = 168,
                        blue = 133
                    ), ColorSampleBoard(
                        red = 129,
                        green = 50,
                        blue = 168
                    )
                )
            ),
            database = DatabaseConfig(dataBasePath = "databasePath")
        )
    }

    @BeforeEach
    fun setup() {
        DummyData.codeChartsConfigCollection.saveOne(DummyData.codeChartsConfigs.first())
    }

    @AfterEach
    fun clearUp() {
        DummyData.codeChartsConfigCollection.deleteMany()
    }

    @Test
    fun `encoding valid config file works`() = try {
        val content = assertDoesNotThrow {
            configManager.writeFileNoThrow()
            configManager.readFile()
        }
        assertThat(Json { prettyPrint = true }.encodeToString(ConfigFile.serializer(), configFile)).isEqualTo(content)
    } finally {
        Files.deleteIfExists(Path.of(TEST_PATH_CONFIG_FILE))
        configManager.writeFileNoThrow(Paths.get(TEST_PATH_CONFIG_FILE))
    }

    @Test
    fun `decoding valid config file works`() {
        configManager.writeFileNoThrow(Path.of(TEST_PATH_CONFIG_FILE))
        val expected = configFile
        val actual = configManager.decodeConfig()?.copy(
            codeChartsConfig = configFile.codeChartsConfig,
            eyeTrackingConfig = configFile.eyeTrackingConfig,
            bubbleViewConfig = configFile.bubbleViewConfig
        )
        assertThat(actual).isEqualTo(expected)

        Files.deleteIfExists(Path.of(TEST_PATH_CONFIG_FILE))
    }
}
