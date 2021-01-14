package de.tuchemnitz.se.exercise.codecharts

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class CodeChartsStringHandlerTest {
    companion object {
        val handleMyStrings = CodeChartsStringHandler()

        @Suppress("unused")
        @JvmStatic
        fun permutations() = (0..7)
            .map { n ->
                Arguments.of(
                    101,
                    StringCharacters(
                        (n and 1) != 0,
                        (n and 2) != 0,
                        (n and 4) != 0
                    )
                )
            }
    }

    @MethodSource("permutations")
    @ParameterizedTest
    fun `check whether strings are duplicate`(inputNumber: Int, conditions: StringCharacters) {
        handleMyStrings.setStrings(inputNumber, conditions)
        assertThat(handleMyStrings.getStrings()).isEqualTo(handleMyStrings.getStrings())
    }

    @MethodSource("permutations")
    @ParameterizedTest
    fun `check whether list is ordered`(inputNumber: Int, conditions: StringCharacters) {
        handleMyStrings.setStrings(inputNumber, conditions)
        val expected = handleMyStrings.getStrings().sort()
        val actual = handleMyStrings.orderList()
        assertThat(expected).isEqualTo(actual)
    }

    @MethodSource("permutations")
    @ParameterizedTest
    fun `check whether number of strings is right`(inputNumber: Int, conditions: StringCharacters) {
        if (conditions == StringCharacters(upperCase = false, lowerCase = false, numbers = false)) {
            handleMyStrings.setStrings(
                inputNumber,
                StringCharacters(upperCase = false, lowerCase = false, numbers = false)
            )
            val generatedStrings = handleMyStrings.getStrings()
            assertThat(generatedStrings.size).isEqualTo(0)
        } else {
            handleMyStrings.setStrings(inputNumber, conditions)
            val generatedStrings = handleMyStrings.getStrings()
            assertThat(inputNumber).isEqualTo(generatedStrings.size)
        }
    }
}
