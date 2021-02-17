package de.tuchemnitz.se.exercise.codecharts

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.tuchemnitz.se.exercise.DummyData
import de.tuchemnitz.se.exercise.core.configmanager.ConfigManager
import de.tuchemnitz.se.exercise.persist.IPersist
import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import de.tuchemnitz.se.exercise.persist.configs.Grid
import de.tuchemnitz.se.exercise.persist.configs.PictureData
import de.tuchemnitz.se.exercise.persist.data.CodeChartsData
import io.mockk.Runs
import io.mockk.clearMocks
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.excludeRecords
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import tornadofx.Controller
import tornadofx.Scope
import tornadofx.set

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CodeChartsConfigMapperTest : Controller() {

    override val scope: Scope = Scope()
    private val mockedConfigManager: ConfigManager = mockk(relaxed = true)
    private val codeChartsConfigMapper: CodeChartsConfigMapper by inject()

    @BeforeAll
    fun injectMocks() {
        scope.set(mockedConfigManager)
    }

    @BeforeEach
    fun resetMocks() {
        clearMocks(mockedConfigManager)
        excludeRecords { mockedConfigManager.paramsProperty }
    }

    @AfterEach
    fun verifyMocks() {
        confirmVerified(mockedConfigManager)
    }

    @Test
    fun `values should be mapped correctly to config and data`() {
        val inputValues = CodeChartsValues(
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

        val expectedConfig = CodeChartsConfig(
            minViewsToSubdivide = 0,
            stringCharacters = StringCharacters(upperCase = false, lowerCase = false, numbers = true),
            pictures = listOf(
                PictureData(
                    imagePath = "",
                    grid = Grid(1, 2),
                    pictureViewTime = 2,
                    relative = false,
                    maxRecursionDepth = 4
                )
            ),
            matrixViewTime = 1,
            ordered = true
        )

        val expectedData = CodeChartsData(
            codeChartsConfig = expectedConfig,
            originalImageSize = Dimension(x = 1.0, y = 2.0),
            scaledImageSize = Dimension(x = 1.0, y = 2.0),
            screenSize = Dimension(x = 1.0, y = 2.0),
            stringPosition = Interval2D(xMax = 2.0, xMin = 0.0, yMax = 9.0, yMin = 0.0),
            currentUser = DummyData.userData.first()
        )

        val saved = mutableListOf<IPersist>()

        every { mockedConfigManager.savePersistable(config = capture(saved)) } just Runs
        codeChartsConfigMapper.saveCodeChartsDatabaseConfig(inputValues)
        verify(exactly = 2) { mockedConfigManager.savePersistable(any()) }
        verify(exactly = 1) { mockedConfigManager.currentUser }

        val savedConfig =
            (saved[1] as CodeChartsConfig).copy(_id = expectedConfig._id, savedAt = expectedConfig.savedAt)
        val savedData = (saved[0] as CodeChartsData).let {
            it.copy(
                _id = expectedData._id,
                codeChartsConfig = it.codeChartsConfig.copy(_id = expectedConfig._id, savedAt = expectedConfig.savedAt),
                currentUser = expectedData.currentUser,
                savedAt = expectedData.savedAt

            )
        }

        assertThat(savedConfig).isEqualTo(expectedConfig)
        assertThat(savedData).isEqualTo(expectedData)
    }
}
