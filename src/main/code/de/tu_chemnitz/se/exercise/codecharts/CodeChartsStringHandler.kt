package de.tu_chemnitz.se.exercise.codecharts

class CodeChartsStringHandler {
<<<<<<< HEAD
  private val generatedStrings = setOf<String>()

  /**
   * @param Input Number of Chars in the generated strings.
   * @param AllowedChars ByteArray with 3 modes in the following order: Upper Letters, Lower Letters, Numbers
   *     where 1 is enabled and 0 is not enabled.
   * @return StringArray
   */
  fun generateStrings(Input: Int, AllowedChars: ByteArray) {}
=======
  private lateinit var generatedStrings: Set<String>
  fun generateStrings(Input: Int) {}
  private fun orderList() {}
  fun isInList(InputString: String) {}
>>>>>>> f0ba02e31c70b68aff5c5ecee789dd1085eb960d
}