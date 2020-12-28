package de.tuchemnitz.se.exercise.codecharts

import java.util.Random
import kotlin.math.ln
import kotlin.math.roundToInt

class CodeChartsStringHandler {
    private lateinit var generatedStrings: MutableList<String>

    fun getStrings(): MutableList<String> {
        return this.generatedStrings
    }

    fun setStrings(input: Int, allowedChars: StringCharacters) {
        val generatedList = generateStringList(input, allowedChars)
        this.generatedStrings = generatedList
    }

    /**
     * [input] Number of Chars in the generated strings.
     * [allowedChars] BooleanArray with 3 modes in the following order: Upper Letters, Lower Letters, Numbers
     *     where 1 is enabled and 0 is not enabled.
     * returns MutableList<String>
     */
    private fun generateStringList(input: Int, allowedChars: StringCharacters): MutableList<String> {
        val generatedStrings = mutableListOf<String>()
        if (allowedChars == StringCharacters(upperCase = false, lowerCase = false, numbers = false)) {
            return generatedStrings
        }
        var i = input

        var alphabetLength = 0
        if (allowedChars.upperCase) {
            alphabetLength += 26
        }
        if (allowedChars.lowerCase) {
            alphabetLength += 26
        }
        if (allowedChars.numbers) {
            alphabetLength += 10
        }
        /*
        Number of possible combinations c for given alphabet a and given string length p is c = a^p
        - we want to calculate p where c is our parameter 'Input' in this case
         */
        val stringLength = ((ln(input.toFloat())) / (ln(alphabetLength.toFloat()))).roundToInt() + 1
        while (i > 0) {
            val generatedString = generateOneString(stringLength, allowedChars)
            if (generatedString !in generatedStrings) { // no duplicates in string set
                generatedStrings.add(generatedString)
                i -= 1
            }
        }
        return generatedStrings
    }

    private fun generateOneString(stringLength: Int, allowedChars: StringCharacters): String {
        if (allowedChars == StringCharacters(upperCase = false, lowerCase = false, numbers = false)) {
            return ""
        }

        var charset = "" // saves allowed characters
        if (allowedChars.upperCase) {
            charset += ('A'..'Z').joinToString("")
        }
        if (allowedChars.lowerCase) {
            charset += ('a'..'z').joinToString("")
        }
        if (allowedChars.numbers) {
            charset += ('0'..'9').joinToString("")
        }

        val charsetLength = charset.length
        val rand = Random()
        return generateSequence { rand.nextInt(charsetLength - 1) } // generates String from given parameters and calculated charset
            .take(stringLength)
            .map { charset[it] }
            .joinToString("")
    }

    fun orderList() {
        this.generatedStrings = ((this.generatedStrings).sorted()).toMutableList()
    }
}
