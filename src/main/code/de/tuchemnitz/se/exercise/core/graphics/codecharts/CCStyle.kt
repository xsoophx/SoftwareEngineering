package de.tuchemnitz.se.exercise.core.graphics.codecharts

import de.tuchemnitz.se.exercise.core.graphics.Style.Companion.row
import javafx.geometry.Pos
import tornadofx.Stylesheet
import tornadofx.box
import tornadofx.cssclass
import tornadofx.px

class CCStyle : Stylesheet() {
    companion object {
        val ccGridWrapper by cssclass()
        val ccPictureWrapper by cssclass()
    }

    init {
        s(ccPictureWrapper) {
            prefHeight = 1000.px
            prefWidth = 1000.px
            s(imageView)
            alignment = Pos.CENTER
        }
    }
}
