package de.tuchemnitz.se.exercise.core.graphics.dataanalyzer

import de.tuchemnitz.se.exercise.core.graphics.MainApp
import javafx.scene.image.Image
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import tornadofx.*

class DataClientOutputView : View("Data Client Output") {
    override val root: BorderPane by fxml(MainApp.MAIN_VIEW_TEMPLATE_PATH)
    private val contentBox: VBox by fxid("content")

    init {
        with(contentBox) {
            imageview {

                image = Image(
                    "output.bmp"
                )
            }
            button("NEXT") {
                action {
                    // jump back into loop and continue
                    // if there are no more datasets: display according view
                }
            }
            button("CLOSE") {
                action {
                    replaceWith(DataClientInitialView::class)
                }
            }
        }
    }
    // needed for git button
    fun printGitButton() {
    }
}
