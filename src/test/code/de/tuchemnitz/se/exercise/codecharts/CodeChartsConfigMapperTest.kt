package de.tuchemnitz.se.exercise.codecharts

import de.tuchemnitz.se.exercise.DummyData
import de.tuchemnitz.se.exercise.core.configmanager.ConfigManager
import de.tuchemnitz.se.exercise.persist.AbstractDatabaseTest
import de.tuchemnitz.se.exercise.persist.data.CodeChartsData
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CodeChartsConfigMapperTest : AbstractDatabaseTest(db = mockk(relaxed = true)) {

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun codeChartsData() = DummyData.codeChartsData()
    }

    @ParameterizedTest
    @MethodSource("codeChartsData")
    fun `config is mapped and saved in database correctly`(input: CodeChartsData) {
        val configManager = mockk<ConfigManager>()
        val mockedResult = mockk<Unit>()

        every { configManager.saveConfig(any()) } returns mockedResult
        configManager.saveConfig(input)
        verify { configManager.saveConfig(input) }
    }
}