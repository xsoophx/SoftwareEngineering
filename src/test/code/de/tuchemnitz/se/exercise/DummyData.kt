package de.tuchemnitz.se.exercise

import de.tuchemnitz.se.exercise.codecharts.Dimension
import de.tuchemnitz.se.exercise.codecharts.Interval2D
import de.tuchemnitz.se.exercise.codecharts.StringCharacters
import de.tuchemnitz.se.exercise.core.configmanager.ConfigManager
import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import de.tuchemnitz.se.exercise.persist.configs.Grid
import de.tuchemnitz.se.exercise.persist.configs.PictureData
import de.tuchemnitz.se.exercise.persist.configs.ZoomMapsConfig
import de.tuchemnitz.se.exercise.persist.configs.collections.CodeChartsConfigCollection
import de.tuchemnitz.se.exercise.persist.configs.collections.ZoomMapsConfigCollection
import de.tuchemnitz.se.exercise.persist.data.CodeChartsData
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
            minViewsToSubdivide = 0,
            stringCharacters = StringCharacters(upperCase = false, lowerCase = true, numbers = false),
            pictures = listOf(
                PictureData(
                    imagePath = "abc",
                    matrixViewTime = 2,
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
            minViewsToSubdivide = 0,
            stringCharacters = StringCharacters(upperCase = false, lowerCase = true, numbers = true),
            pictures = listOf(
                PictureData(
                    imagePath = "def",
                    matrixViewTime = 2,
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
            minViewsToSubdivide = 40,
            stringCharacters = StringCharacters(upperCase = false, lowerCase = false, numbers = true),
            pictures = listOf(
                PictureData(
                    imagePath = "",
                    grid = Grid(13, 15),
                    pictureViewTime = 3,
                    matrixViewTime = 4,
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

    @get: JvmStatic
    val codeChartsData = setOf(
        CodeChartsData(
            codeChartsConfig = codeChartsConfigs.first(),
            originalImageSize = Dimension(x = 3.0, y = 4.0),
            scaledImageSize = Dimension(x = 100.0, y = 200.0),
            screenSize = Dimension(x = 1920.0, y = 1080.0),
            stringPosition = Interval2D(xMin = 0.0, xMax = 3.0, yMin = 0.0, yMax = 3.0)
        ),
        CodeChartsData(
            codeChartsConfig = codeChartsConfigs.last(),
            originalImageSize = Dimension(x = 123.0, y = 456.0),
            scaledImageSize = Dimension(x = 125.0, y = 500.0),
            screenSize = Dimension(x = 1920.0, y = 1080.0),
            stringPosition = Interval2D(xMin = 10.0, xMax = 4.0, yMin = 6.2, yMax = 7.55)
        ),
    )

    @JvmStatic
    fun codeChartsData() = codeChartsData.stream()
}
