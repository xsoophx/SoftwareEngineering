package de.tuchemnitz.se.exercise.codecharts

import de.tuchemnitz.se.exercise.DummyData
import de.tuchemnitz.se.exercise.core.configmanager.ConfigManager
import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import de.tuchemnitz.se.exercise.persist.configs.Grid
import de.tuchemnitz.se.exercise.persist.configs.PictureData
import de.tuchemnitz.se.exercise.persist.data.CodeChartsData
import io.mockk.clearMocks
import io.mockk.confirmVerified
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import tornadofx.Controller
import tornadofx.set

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockKExtension::class)
class CodeChartsConfigMapperTest : Controller() {

    private val configManager: ConfigManager = mockk(relaxed = true)

    init {
        scope.set(configManager)
    }

    private val codeChartsConfigMapper: CodeChartsConfigMapper by inject()

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun codeChartsData() = DummyData.codeChartsData()

        private val codeChartsValues = CodeChartsValues(
            imagePath = "",
            originalImageSize = Dimension(x = 1.0, y = 2.0),
            scaledImageSize = Dimension(x = 1.0, y = 2.0),
            screenSize = Dimension(x = 1.0, y = 2.0),
            gridDimension = Dimension(x = 1.0, y = 2.0),
            matrixViewTime = 1.0,
            pictureViewTime = 2.0,
            relative = false,
            recursionDepth = 4,
            allowedChars = StringCharacters(upperCase = false, lowerCase = false, numbers = true),
            sorted = true,
            eyePos = Interval2D(xMax = 2.0, xMin = 0.0, yMax = 9.0, yMin = 0.0)
        )

        private val mappedCodeChartsData = CodeChartsConfigMapper().saveCodeChartsDatabaseConfig(codeChartsValues)

        private val codeChartsData = CodeChartsData(
            codeChartsConfig = CodeChartsConfig(
                minViewsToSubdivide = 0,
                stringCharacters = StringCharacters(upperCase = false, lowerCase = false, numbers = true),
                pictures = listOf(
                    PictureData(
                        imagePath = "",
                        matrixViewTime = 1,
                        grid = Grid(1, 2),
                        pictureViewTime = 2,
                        ordered = true,
                        relative = false,
                        maxRecursionDepth = 4
                    )
                )
            ),
            originalImageSize = Dimension(x = 1.0, y = 2.0),
            scaledImageSize = Dimension(x = 1.0, y = 2.0),
            screenSize = Dimension(x = 1.0, y = 2.0),
            stringPosition = Interval2D(xMin = 0.0, xMax = 2.0, yMin = 0.0, yMax = 9.0)
        )
    }

    @AfterEach
    fun cleanUp() {
        confirmVerified(configManager)
    }

    @BeforeEach
    fun setup() {
        clearMocks(configManager)
    }

    // TODO:
    /* @Test
     fun `saveConfig is invoked by mapper`() {
         every { configManager.saveConfig(any()) } just Runs
         codeChartsConfigMapper.saveCodeChartsDatabaseConfig(codeChartsValues)
         verify { configManager.saveConfig(any()) }
         verify { configManager.saveConfig(any()) }
     }*/
}