package de.tuchemnitz.se.exercise.core.graphics

import tornadofx.Stylesheet
import tornadofx.box
import tornadofx.cssclass
import tornadofx.px

class Style : Stylesheet() {

    companion object {
        val pictureWrapper by cssclass()
        val row by cssclass()
        val mainWrapper by cssclass()
    }

    init {

        s(pictureWrapper) {
            prefHeight = 1000.px
            prefWidth = 1000.px
            s(imageView)
            padding = box(15.px)
            s(row) {
                padding = box(10.px, 5.px)
            }
        }

        s(mainWrapper) {
            prefHeight = 300.px
            prefWidth = 300.px
        }
    }
}
