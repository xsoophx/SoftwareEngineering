package de.tu_chemnitz.se.exercise.core.graphics

import javafx.scene.layout.VBox
import tornadofx.View

class MainView : View() {
  override val root = VBox()

  init{
    val picture = Picture(root)
  }
}