package de.tuchemnitz.se.exercise.codecharts

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class CodeChartsStringHandlerTest {
    @Test
    fun `check whether strings are duplicate`() {
        val handleMyStrings = CodeChartsStringHandler()
        handleMyStrings.setStrings(101, booleanArrayOf(true, true, true))
        var generatedStringList = handleMyStrings.getStrings()
        assertThat(generatedStringList).isEqualTo(generatedStringList)

        handleMyStrings.setStrings(101, booleanArrayOf(true, true, false))
        generatedStringList = handleMyStrings.getStrings()
        assertThat(generatedStringList).isEqualTo(generatedStringList)

        handleMyStrings.setStrings(101, booleanArrayOf(true, false, true))
        generatedStringList = handleMyStrings.getStrings()
        assertThat(generatedStringList).isEqualTo(generatedStringList)

        handleMyStrings.setStrings(101, booleanArrayOf(true, false, false))
        generatedStringList = handleMyStrings.getStrings()
        assertThat(generatedStringList).isEqualTo(generatedStringList)

        handleMyStrings.setStrings(101, booleanArrayOf(false, true, true))
        generatedStringList = handleMyStrings.getStrings()
        assertThat(generatedStringList).isEqualTo(generatedStringList)

        handleMyStrings.setStrings(101, booleanArrayOf(false, true, false))
        generatedStringList = handleMyStrings.getStrings()
        assertThat(generatedStringList).isEqualTo(generatedStringList)

        handleMyStrings.setStrings(101, booleanArrayOf(false, false, true))
        generatedStringList = handleMyStrings.getStrings()
        assertThat(generatedStringList).isEqualTo(generatedStringList)

        handleMyStrings.setStrings(101, booleanArrayOf(false, false, false))
        generatedStringList = handleMyStrings.getStrings()
        assertThat(generatedStringList).isEqualTo(generatedStringList)
    }

    @Test
    fun `check whether generated strings have the right format - utf8 ascii etc-`() {
    }

    @Test
    fun `check whether list is ordered`() {
        val handleMyStrings = CodeChartsStringHandler()
        handleMyStrings.setStrings(101, booleanArrayOf(true, true, true))
        val generatedStrings = handleMyStrings.getStrings()
        handleMyStrings.orderList()
        val generatedStringsOrdered = handleMyStrings.getStrings()
        generatedStrings.sort()
        assertThat(generatedStringsOrdered).isEqualTo(generatedStrings)
    }

    @Test
    fun `check whether number of strings is right`() {
        val input = 701
        val handleMyStrings = CodeChartsStringHandler()
        handleMyStrings.setStrings(input, booleanArrayOf(true, true, true))
        val generatedStrings = handleMyStrings.getStrings()
        assertThat(input).isEqualTo(generatedStrings.size)
    }
}
