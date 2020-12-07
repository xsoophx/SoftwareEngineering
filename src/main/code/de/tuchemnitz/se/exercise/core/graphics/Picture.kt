package de.tuchemnitz.se.exercise.core.graphics

import javafx.scene.layout.VBox
import tornadofx.CssRule
import tornadofx.View
import tornadofx.addClass
import tornadofx.imageview

class Picture(
    private val path: String = "/cross.png",
    pictureTitle: String = "",
    private val cssRule: CssRule = Style.pictureWrapper
) : View() {

    override val root = VBox()

    init {
        title = pictureTitle
        with(root) {
            addClass(cssRule)
            imageview(path)
        }
    }
}
