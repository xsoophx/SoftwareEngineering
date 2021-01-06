package de.tuchemnitz.se.exercise.codecharts

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.containsOnly
import assertk.assertions.isEqualTo
import de.tuchemnitz.se.exercise.DummyData
import de.tuchemnitz.se.exercise.core.configmanager.ConfigManager
import de.tuchemnitz.se.exercise.persist.Database
import de.tuchemnitz.se.exercise.persist.data.CodeChartsData
import de.tuchemnitz.se.exercise.persist.data.collections.CodeChartsDataCollection
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.clearMocks
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.litote.kmongo.eq
import tornadofx.Controller
import tornadofx.set

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockKExtension::class)
class CodeChartsConfigMapperTest : Controller() {

    private val configManager: ConfigManager = mockk()

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
    }

    @AfterEach
    fun cleanUp() {
        confirmVerified(configManager)
    }

    @BeforeEach
    fun setup() {
        clearMocks(configManager)
    }

  /*  @Test
    fun `saveConfig is invoked by mapper`() {
        every { configManager.saveConfig(any()) } just Runs
        codeChartsConfigMapper.saveCodeChartsDatabaseConfig(codeChartsValues)
        verify { configManager.saveConfig(codeChartsValues) }
        verify { configManager.saveConfig(input) }
    }*/

}