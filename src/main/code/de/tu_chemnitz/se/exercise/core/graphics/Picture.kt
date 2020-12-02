package de.tu_chemnitz.se.exercise.core.graphics

import javafx.scene.Node
import javafx.scene.layout.VBox
import tornadofx.*
import tornadofx.Stylesheet.Companion.title


class Picture(private val path: String = "/cat.jpg", private val pictureTitle: String = "") : Node() {

  override val root = VBox()

  init {
    title = pictureTitle

    with(root) {
      addClass(Style.wrapper)

      button("Click here I'm a button")
      label("Im a label")
      imageview(path)
      children.addClass(Style.row)
    }
  }

  fun load(path: String) {

  }

  fun render() {

  }

}

