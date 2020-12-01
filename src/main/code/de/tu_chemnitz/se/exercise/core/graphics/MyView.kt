package de.tu_chemnitz.se.exercise.core.graphics

import tornadofx.View
import tornadofx.button
import tornadofx.label
import tornadofx.vbox


class MyView : View() {
  override val root = vbox{

    button("Click here I'm a button")
    label("Im a label")
  }

}
