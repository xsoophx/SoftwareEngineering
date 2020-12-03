package de.tu_chemnitz.se.exercise.core.graphics

import javafx.scene.layout.VBox
import tornadofx.*


class MainView : View("Eyetracking Tool") {
  override val root = VBox()

  init {
    with(root) {
      addClass(Style.mainWrapper)
      button("Click here I'm a button"){
        action {
          this@MainView.replaceWith(Picture::class)
        }
      }
    }
  }
}