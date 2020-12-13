package de.tuchemnitz.se.exercise.core.graphics

import javafx.geometry.Pos
import javafx.scene.paint.Color
import tornadofx.Stylesheet
import tornadofx.box
import tornadofx.cssclass
import tornadofx.px
import javax.swing.GroupLayout

class Style : Stylesheet() {

    companion object {
        val pictureWrapper by cssclass()
        val row by cssclass()
        val mainWrapper by cssclass()
        val mainTopStyle by cssclass()
        val mainCenterStyle by cssclass()
        val mainBottomStyle by cssclass()
        val mainBottomButtonStyle by cssclass()
    }

    init {

        s(mainTopStyle) {
            backgroundColor += Color.DIMGREY
            padding = box(10.px, 10.px)
        }

        s(mainCenterStyle) {
            backgroundColor += Color.GREY
        }

        s(mainBottomStyle) {
            backgroundColor += Color.DIMGREY
            padding = box(10.px, 10.px)
            alignment = Pos.CENTER
        }

        s(mainBottomButtonStyle) {
            textFill = Color.LIGHTGREY
            borderColor += box(Color.LIGHTGREY)
            backgroundColor += Color.DARKGREY
            // alignment = Pos.CENTER
        }

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
