package de.tuchemnitz.se.exercise.core.graphics.codecharts

import de.tuchemnitz.se.exercise.core.graphics.codecharts.CCStyle.Companion.ccPictureWrapper
// import javafx.scene.layout.VBox
import tornadofx.CssRule
import tornadofx.View
import tornadofx.addClass
import tornadofx.imageview
import tornadofx.vbox

class CCPictureView(
    private val path: String = "/Chameleon.jpg",
    pictureTitle: String = "",
    private val cssRule: CssRule = ccPictureWrapper,
) : View("Software Praktikum - CodeCharts Picture") {
    override val root = vbox {
        title = pictureTitle
        addClass(cssRule)
        imageview(path)
    }
}
