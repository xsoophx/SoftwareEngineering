package de.tuchemnitz.se.exercise.core.graphics

import javafx.scene.layout.VBox
import tornadofx.View
import tornadofx.action
import tornadofx.addClass
import tornadofx.button

class MainView : View("Eyetracking Tool") {
    override val root = VBox()

    init {
        with(root) {
            addClass(Style.mainWrapper)
            button("Click here I'm a button") {
                action {
                    replaceWith(Picture::class)
                }
            }
        }
    }
}
