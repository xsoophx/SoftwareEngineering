package de.tuchemnitz.se.exercise.core.graphics.codecharts

import de.tuchemnitz.se.exercise.core.graphics.Style.Companion.row
import javafx.geometry.Pos
import javafx.scene.paint.Color
import tornadofx.Stylesheet
import tornadofx.box
import tornadofx.cssclass
import tornadofx.px
import javax.swing.GroupLayout

class CCStyle: Stylesheet() {
    companion object {
        val ccPictureWrapper by cssclass()
    }
    init {
        s(ccPictureWrapper) {
            prefHeight = 1000.px
            prefWidth = 1000.px
            s(imageView)
            padding = box(15.px)
            s(row) {
            padding = box(10.px, 5.px)
      }
    }
  }
}
