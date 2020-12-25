package de.tuchemnitz.se.exercise.core.graphics

import javafx.scene.control.Button
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.TextAlignment
import org.slf4j.LoggerFactory
import tornadofx.View
import tornadofx.action
import tornadofx.addClass
import tornadofx.borderpane
import tornadofx.button
import tornadofx.hbox
import tornadofx.text
import tornadofx.textflow
import tornadofx.tooltip
import tornadofx.vbox

/*
class MainView : View("Software Engineering - UI") {
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
*/

class MainPageView : View("Software Praktikum - Gruppe 4") {
    companion object {
        val logger = LoggerFactory.getLogger(MainPageView::class.java)
    }

    override val root = vbox {
        borderpane {
            top = vbox {
                textflow {
                    textAlignment = TextAlignment.CENTER
                    text("Software Praktikum ") {
                        fill = Color.LIMEGREEN
                        font = Font(32.0)
                    }
                    text("Gruppe 4") {
                        fill = Color.AQUAMARINE
                        font = Font(24.0)
                    }
                }
                addClass(Style.mainTopStyle)
            }

            bottom = hbox(15) {
                button("Our GitHub") {
                    tooltip("Link to GitHub")
                    action {
                        println("Button pressed!")
                    }
                }
                button("Help") {
                    tooltip("Get assistance")
                    action {
                        println("Button pressed!")
                    }
                }
                addClass(Style.mainBottomStyle)
                children.asSequence()
                    .filter { it is Button }
                    .forEach { it.addClass(Style.mainBottomButtonStyle) }
            }

            /*
            left = vbox {

            }
             */

            /*
            right = vbox {

            }
             */

            center = vbox {
                button("Login") {
                    tooltip("Login Button")
                    action {
                        replaceWith(LoginView::class)
                    }
                }
                addClass(Style.mainCenterStyle)
            }
        }
    }

    override fun onDock() {
        logger.info("Docking Main Page!")
    }

    override fun onUndock() {
        logger.info("Undocking Main Page!")
    }
}
