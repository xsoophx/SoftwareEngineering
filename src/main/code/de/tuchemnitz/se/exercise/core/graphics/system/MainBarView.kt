package de.tuchemnitz.se.exercise.core.graphics.system

import de.tuchemnitz.se.exercise.core.configmanager.ConfigManager
import de.tuchemnitz.se.exercise.core.graphics.MainApp
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import tornadofx.View
import tornadofx.action
import java.io.IOException

open class MainBarView(viewTitle: String) : View(viewTitle) {
    private val configManager: ConfigManager by inject()
    override val root: BorderPane by fxml(MainApp.MAIN_VIEW_TEMPLATE_PATH)
    val contentBox: VBox by fxid("content")
    val exitButton: Button by fxid("exit_button")
    val mainMenuButton: Button by fxid("main_menu_button")
    private val saveConfigFileButton: Button by fxid("create_json_button")

    init {
        with(exitButton) {
            action {
                writeConfigFile()
                primaryStage.close()
            }
        }

        with(mainMenuButton) {
            action {
                writeConfigFile()
                replaceWith(ToolSelectionView::class)
            }
        }

        with(saveConfigFileButton) {
            action {
                writeConfigFile(showSuccess = true)
            }
        }
    }

    private fun writeConfigFile(showSuccess: Boolean = false) {
        try {
            configManager.writeFile()
            if (showSuccess) {
                Alert(Alert.AlertType.INFORMATION).apply {
                    headerText = "Success!"
                    val message = "Your config was successfully written!"
                    dialogPane.contentText = message
                }.showAndWait()
            }
        } catch (e: IOException) {
            Alert(Alert.AlertType.ERROR).apply {
                headerText = "Error!"
                val message = "An error while writing the Config File occurred! ${e.localizedMessage}"
                dialogPane.contentText = message
            }.showAndWait()
        }
    }
}

