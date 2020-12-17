package de.tuchemnitz.se.exercise.core.configmanager

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.tuchemnitz.se.exercise.DummyData
import de.tuchemnitz.se.exercise.persist.configs.EyeTrackingConfig
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Path

class ConfigFileEncoderTest {
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
            bubbleViewConfig = BubbleViewConfig(filter = setOf(0F))
        )
        val actual = DummyData.manager.configFileEncoder.assembleAllConfigurations()
            .copy(
                eyeTrackingConfig = expected.eyeTrackingConfig,
                bubbleViewConfig = expected.bubbleViewConfig
            )
        assertThat(actual).isEqualTo(expected)
        DummyData.manager.writeFile(Path.of("cfg.json"), DummyData.manager.configFileEncoder.configFile())
    }

    private val mostRecentCodeChartsConfig =
        DummyData.codeChartsConfigs.maxByOrNull { it.savedAt }

    private val mostRecentZoomMapsConfig =
        DummyData.zoomMapsConfigs.maxByOrNull { it.savedAt }
}
