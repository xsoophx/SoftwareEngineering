package de.tuchemnitz.se.exercise

import de.tuchemnitz.se.exercise.codecharts.Dimension
import de.tuchemnitz.se.exercise.codecharts.Interval2D
import de.tuchemnitz.se.exercise.codecharts.StringCharacters
import de.tuchemnitz.se.exercise.core.configmanager.ConfigManager
import de.tuchemnitz.se.exercise.persist.Database
import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import de.tuchemnitz.se.exercise.persist.configs.Grid
import de.tuchemnitz.se.exercise.persist.configs.PictureData
import de.tuchemnitz.se.exercise.persist.configs.ZoomMapsConfig
import de.tuchemnitz.se.exercise.persist.configs.collections.CodeChartsConfigCollection
import de.tuchemnitz.se.exercise.persist.configs.collections.ZoomMapsConfigCollection
import de.tuchemnitz.se.exercise.persist.data.CodeChartsData
import de.tuchemnitz.se.exercise.persist.data.Gender
import de.tuchemnitz.se.exercise.persist.data.UserData
import de.tuchemnitz.se.exercise.persist.data.VisionImpaired
import de.tuchemnitz.se.exercise.persist.data.ZoomMapsData
import de.tuchemnitz.se.exercise.persist.data.collections.ZoomMapsDataCollection
import de.tuchemnitz.se.exercise.persist.now
import javafx.geometry.Point2D
import javafx.scene.input.KeyCode
import tornadofx.Controller
import tornadofx.Scope
import tornadofx.set

const val TEST_PATH_CONFIG_FILE = "testCfg.json"

object DummyData : Controller() {

    override val scope: Scope = Scope().apply {
        set(Database(databaseName = "test"))
    }
    val configManager: ConfigManager by inject()
    val codeChartsConfigCollection: CodeChartsConfigCollection by inject()
    val zoomMapsConfigCollection: ZoomMapsConfigCollection by inject()
    val zoomMapsDataCollection: ZoomMapsDataCollection by inject()
    private val baseTime = now()

    init {
        configManager.configFilePath = TEST_PATH_CONFIG_FILE
    }

    @get: JvmStatic
    val codeChartsConfigs = listOf(
        CodeChartsConfig(
            savedAt = baseTime.plusSeconds(1L),
            minViewsToSubdivide = 0,
            stringCharacters = StringCharacters(upperCase = false, lowerCase = true, numbers = false),
            pictures = listOf(
                PictureData(
                    imagePath = "abc",
                    grid = Grid(10, 20),
                    pictureViewTime = 2,
                    relative = false,
                    maxRecursionDepth = 3
                )
            ),
            matrixViewTime = 2,
            ordered = true
        ),
        CodeChartsConfig(
            savedAt = baseTime.plusSeconds(2L),
            minViewsToSubdivide = 0,
            stringCharacters = StringCharacters(upperCase = false, lowerCase = true, numbers = true),
            pictures = listOf(
                PictureData(
                    imagePath = "def",
                    grid = Grid(100, 200),
                    pictureViewTime = 5,
                    relative = false,
                    maxRecursionDepth = 0
                )
            ),
            matrixViewTime = 2,
            ordered = false
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
                    relative = true,
                    maxRecursionDepth = 4
                )
            ),
            matrixViewTime = 4,
            ordered = true
        )
    )

    @JvmStatic
    @Suppress("unused")
    fun codeChartsConfigsSource() = codeChartsConfigs.stream()

    val userData = listOf(
        UserData(
            firstName = "Klaus",
            lastName = "Nikolaus",
            age = 61,
            gender = Gender.Male,
            visionImpaired = VisionImpaired.Yes
        ),
        UserData(
            firstName = "Winter",
            lastName = "Kind",
            age = 14,
            gender = Gender.Diverse,
            visionImpaired = VisionImpaired.Unselected
        ),
        UserData(
            firstName = "Klaus",
            lastName = "Kek",
            age = 25,
            gender = Gender.Female,
            visionImpaired = VisionImpaired.No
        )
    )

    @get: JvmStatic
    val zoomMapsConfigs = setOf(
        ZoomMapsConfig(
            zoomSpeed = 1.0,
            zoomKey = KeyCode.C,
            savedAt = baseTime.plusSeconds(1L)
        ),
        ZoomMapsConfig(
            zoomSpeed = 2.0,
            zoomKey = KeyCode.A,
            savedAt = baseTime.plusSeconds(2L)
        ),
        ZoomMapsConfig(
            zoomSpeed = 10.0,
            zoomKey = KeyCode.B,
            savedAt = baseTime.plusSeconds(3L)
        )
    )

    @get: JvmStatic
    val zoomMapsData = setOf(
        ZoomMapsData(
            zoomSpeed = 2.0,
            zoomKey = KeyCode.A,
            savedAt = baseTime.plusSeconds(1L),
            image = "aPath",
            currentUser = userData.first(),
            zoomPosition = Point2D(1.0, 1.0)
        ),
        ZoomMapsData(
            zoomSpeed = 1.0,
            zoomKey = KeyCode.C,
            savedAt = baseTime.plusSeconds(2L),
            image = "anotherPath",
            currentUser = userData.first(),
            zoomPosition = Point2D(1.0, 1.0)
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
            stringPosition = Interval2D(xMin = 0.0, xMax = 3.0, yMin = 0.0, yMax = 3.0),
            currentUser = userData.first()
        ),
        CodeChartsData(
            codeChartsConfig = codeChartsConfigs.last(),
            originalImageSize = Dimension(x = 123.0, y = 456.0),
            scaledImageSize = Dimension(x = 125.0, y = 500.0),
            screenSize = Dimension(x = 1920.0, y = 1080.0),
            stringPosition = Interval2D(xMin = 10.0, xMax = 4.0, yMin = 6.2, yMax = 7.55),
            currentUser = userData.last()
        )
    )
}
