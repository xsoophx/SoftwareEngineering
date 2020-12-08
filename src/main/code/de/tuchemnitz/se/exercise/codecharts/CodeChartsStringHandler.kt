package de.tuchemnitz.se.exercise.codecharts

import java.util.Random
import kotlin.math.ln
import kotlin.math.roundToInt

class CodeChartsStringHandler() {
    private lateinit var generatedStrings: MutableList<String>

    fun getStrings(): MutableList<String> {
        return this.generatedStrings
    }

    fun setStrings(input: Int, allowedChars: BooleanArray) {
        val generatedList = generateStringList(input, allowedChars)
        this.generatedStrings = generatedList
        return
    }

    /**
     * [input] Number of Chars in the generated strings.
     * [allowedChars] BooleanArray with 3 modes in the following order: Upper Letters, Lower Letters, Numbers
     *     where 1 is enabled and 0 is not enabled.
     * returns MutableList<String>
     */
    private fun generateStringList(input: Int, allowedChars: BooleanArray): MutableList<String> {
        val generatedStrings = mutableListOf<String>()
        if (allowedChars contentEquals booleanArrayOf(false, false, false)) {
            generatedStrings.add("")
            return generatedStrings
        }
        var i = input

        var alphabetLength = 0
        if (allowedChars[0]) {
            alphabetLength += 26
        }
        if (allowedChars[1]) {
            alphabetLength += 26
        }
        if (allowedChars[2]) {
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

    private fun generateOneString(stringLength: Int, allowedChars: BooleanArray): String {
        if (allowedChars contentEquals booleanArrayOf(false, false, false)) {
            return ""
        }

        var charset = "" // saves allowed characters
        if (allowedChars[0]) {
            charset += ('A'..'Z').joinToString("")
        }
        if (allowedChars[1]) {
            charset += ('a'..'z').joinToString("")
        }
        if (allowedChars[2]) {
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
