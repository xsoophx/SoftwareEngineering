package de.tuchemnitz.se.exercise.core.graphics.system

import de.tuchemnitz.se.exercise.core.graphics.MainApp
import javafx.scene.control.Button
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import tornadofx.View
import tornadofx.action

open class MainBarView(viewTitle: String) : View(viewTitle) {
    override val root: BorderPane by fxml(MainApp.MAIN_VIEW_TEMPLATE_PATH)
    val contentBox: VBox by fxid("content")
    val exitButton: Button by fxid("exit_button")
    val mainMenuButton: Button by fxid("main_menu_button")

    init {
        with(exitButton) {
            action {
                primaryStage.close()
            }
        }

        with(mainMenuButton) {
            action {
                replaceWith(ToolSelectionView::class)
            }
        }
    }
}
