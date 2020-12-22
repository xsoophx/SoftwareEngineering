package de.tuchemnitz.se.exercise.codecharts.graphics

import javafx.geometry.Pos
import tornadofx.Stylesheet
import tornadofx.cssclass
import tornadofx.px

class CodeChartsStyle : Stylesheet() {
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
