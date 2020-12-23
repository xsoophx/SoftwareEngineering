package de.tuchemnitz.se.exercise

import de.tuchemnitz.se.exercise.core.configmanager.ConfigManager
import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import de.tuchemnitz.se.exercise.persist.configs.ZoomMapsConfig
import de.tuchemnitz.se.exercise.persist.configs.collections.CodeChartsConfigCollection
import de.tuchemnitz.se.exercise.persist.configs.collections.ZoomMapsConfigCollection
import de.tuchemnitz.se.exercise.persist.now
import org.litote.kmongo.KMongo
import java.nio.file.Path

const val TEST_PATH_CONFIG_FILE = "testCfg.json"

object DummyData {
    private val client = KMongo.createClient()
    private val database = client.getDatabase("test")
    val manager = ConfigManager(configFilePath = TEST_PATH_CONFIG_FILE, database = database)
    val codeChartsConfigCollection = CodeChartsConfigCollection(database)
    val zoomMapsConfigCollection = ZoomMapsConfigCollection(database)
    private val baseTime = now()

    @get: JvmStatic
    val codeChartsConfigs = setOf(
        CodeChartsConfig(
            grid = Pair(100, 200),
            pictureViewTime = 1,
            matrixViewTime = 2,
            savedAt = baseTime.plusSeconds(1L)
        ),
        CodeChartsConfig(
            grid = Pair(0, 0),
            pictureViewTime = 0,
            matrixViewTime = 0,
            savedAt = baseTime.plusSeconds(2L)
        ),
        CodeChartsConfig(
            grid = Pair(400, 400),
            pictureViewTime = 4,
            matrixViewTime = 4,
            savedAt = baseTime.plusSeconds(3L)
        )
    )

    @JvmStatic
    @Suppress("unused")
    fun codeChartsConfigsSource() = codeChartsConfigs.stream()

    @get: JvmStatic
    val zoomMapsConfigs = setOf(
        ZoomMapsConfig(
            zoomSpeed = 1F,
            keyBindings = setOf("a", "b", "c", "d"),
            savedAt = baseTime.plusSeconds(1L)
        ),
        ZoomMapsConfig(
            zoomSpeed = 2F,
            keyBindings = setOf("d", "e", "f", "g"),
            savedAt = baseTime.plusSeconds(2L)
        ),
        ZoomMapsConfig(
            zoomSpeed = 10F,
            keyBindings = setOf("v", "w", "x", "y"),
            savedAt = baseTime.plusSeconds(3L)
        )
    )

    @JvmStatic
    fun zoomMapsConfigs() = zoomMapsConfigs.stream()
}
