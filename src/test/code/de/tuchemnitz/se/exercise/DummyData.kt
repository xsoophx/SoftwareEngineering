package de.tuchemnitz.se.exercise

import de.tuchemnitz.se.exercise.core.configmanager.ConfigManager
import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import de.tuchemnitz.se.exercise.persist.configs.ZoomMapsConfig
import de.tuchemnitz.se.exercise.persist.configs.collections.CodeChartsConfigCollection
import de.tuchemnitz.se.exercise.persist.configs.collections.ZoomMapsConfigCollection
import de.tuchemnitz.se.exercise.persist.now
import javafx.scene.input.KeyCode
import org.litote.kmongo.KMongo
import tornadofx.Controller

const val TEST_PATH_CONFIG_FILE = "testCfg.json"

object DummyData : Controller() {
    private val client = KMongo.createClient()
    private val database = client.getDatabase("test")
    val manager = ConfigManager(configFilePath = TEST_PATH_CONFIG_FILE, database = database)
    val codeChartsConfigCollection: CodeChartsConfigCollection by inject()
    val zoomMapsConfigCollection: ZoomMapsConfigCollection by inject()
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
            zoomKey = KeyCode.C,
            savedAt = baseTime.plusSeconds(1L)
        ),
        ZoomMapsConfig(
            zoomSpeed = 2F,
            zoomKey = KeyCode.A,
            savedAt = baseTime.plusSeconds(2L)
        ),
        ZoomMapsConfig(
            zoomSpeed = 10F,
            zoomKey = KeyCode.B,
            savedAt = baseTime.plusSeconds(3L)
        )
    )

    @JvmStatic
    fun zoomMapsConfigs() = zoomMapsConfigs.stream()
}
