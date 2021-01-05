package de.tuchemnitz.se.exercise

import de.tuchemnitz.se.exercise.codecharts.StringCharacters
import de.tuchemnitz.se.exercise.core.configmanager.ConfigManager
import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import de.tuchemnitz.se.exercise.persist.configs.Grid
import de.tuchemnitz.se.exercise.persist.configs.PictureData
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
            savedAt = baseTime.plusSeconds(1L),
            matrixViewTime = 2,
            minViewsToSubdivide = 0,
            stringCharacters = StringCharacters(upperCase = false, lowerCase = true, numbers = false),
            pictures = listOf(
                PictureData(
                    imagePath = "abc",
                    grid = Grid(10, 20),
                    pictureViewTime = 2,
                    ordered = true,
                    relative = false,
                    maxRecursionDepth = 3
                )
            )
        ),
        CodeChartsConfig(
            savedAt = baseTime.plusSeconds(2L),
            matrixViewTime = 2,
            minViewsToSubdivide = 0,
            stringCharacters = StringCharacters(upperCase = false, lowerCase = true, numbers = true),
            pictures = listOf(
                PictureData(
                    imagePath = "def",
                    grid = Grid(100, 200),
                    pictureViewTime = 5,
                    ordered = false,
                    relative = false,
                    maxRecursionDepth = 0
                )
            )
        ),
        CodeChartsConfig(
            savedAt = baseTime.plusSeconds(3L),
            matrixViewTime = 4,
            minViewsToSubdivide = 40,
            stringCharacters = StringCharacters(upperCase = false, lowerCase = false, numbers = true),
            pictures = listOf(
                PictureData(
                    imagePath = "",
                    grid = Grid(13, 15),
                    pictureViewTime = 3,
                    ordered = true,
                    relative = true,
                    maxRecursionDepth = 4
                )
            )
        ),
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
