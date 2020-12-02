package de.tu_chemnitz.se.exercise.core

import de.tu_chemnitz.se.exercise.core.graphics.Picture
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class PictureTest {

  private val picture = Picture("", "A Title")

  companion object {
    @JvmStatic
    @Suppress("unused")
    fun providePaths() : Stream<Arguments> = Stream.of(
      Arguments.of("cat.jpg"),
      Arguments.of(""),
      Arguments.of("")
    )
  }

  @ParameterizedTest
  @MethodSource("providePaths")
  fun `picture should be properly loaded`(path: String){
    picture.load(path)
  }

  @ParameterizedTest
  @MethodSource("providePaths")
  fun `picture should be properly rendered`(path: String){
    picture.render()
  }
}