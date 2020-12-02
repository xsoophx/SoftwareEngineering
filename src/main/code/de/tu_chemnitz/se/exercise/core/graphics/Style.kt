package de.tu_chemnitz.se.exercise.core.graphics

import tornadofx.Stylesheet
import tornadofx.cssclass
import tornadofx.px
import tornadofx.box

class Style : Stylesheet() {

  companion object {
    val wrapper by cssclass()
    val row by cssclass()
  }

  init {
    s(wrapper) {
      s(imageView){
        prefHeight = 600.px
        prefWidth = 800.px
      }

      padding = box(15.px)

      s(row){
        padding = box(10.px, 5 .px)
      }
    }
  }
}