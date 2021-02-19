package de.tuchemnitz.se.exercise.core.graphics.system

import de.tuchemnitz.se.exercise.core.configmanager.ConfigManager
import de.tuchemnitz.se.exercise.core.graphics.MainApp
import de.tuchemnitz.se.exercise.core.graphics.zoommaps.ZoomMapsView.Companion.logger
import javafx.geometry.Rectangle2D
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import tornadofx.View
import tornadofx.action
import java.io.IOException

open class MainBarView(viewTitle: String) : View(viewTitle) {

    companion object {
        const val BACkGROUND_IMAGE_WIDTH = 1920.0
        const val BACKGROUND_IMAGE_HEIGHT = 1080.0
        const val BACKGROUND_IMAGE_RATIO = BACkGROUND_IMAGE_WIDTH / BACKGROUND_IMAGE_HEIGHT
    }

    private val configManager: ConfigManager by inject()
    final override val root: BorderPane by fxml(MainApp.MAIN_VIEW_TEMPLATE_PATH)
    val contentBox: VBox by fxid("content")
    val exitButton: Button by fxid("exit_button")
    val mainMenuButton: Button by fxid("main_menu_button")
    private val saveConfigFileButton: Button by fxid("create_json_button")
    private val backgroundImage: ImageView by fxid("background")

    init {
        root.widthProperty().addListener { _ ->
            scaleBackgroundImage()
        }
        root.heightProperty().addListener { _ ->
            scaleBackgroundImage()
        }

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

    private fun scaleBackgroundImage() {
        if (root.width == 0.0 || root.height == 0.0) {
            return
        }
        val width = root.width
        val height = root.height - 90.0 // 90.0 = 32.0 (top height) + 58.0 (bottom height)
        logger.info("ImageView size: ($width, $height)")
        val aspectRatio = width / height
        if (aspectRatio <= BACKGROUND_IMAGE_RATIO) {
            val visibleWidth = BACKGROUND_IMAGE_HEIGHT * aspectRatio
            backgroundImage.viewport =
                Rectangle2D((BACkGROUND_IMAGE_WIDTH - visibleWidth) * 0.5, 0.0, visibleWidth, BACKGROUND_IMAGE_HEIGHT)
        } else {
            val visibleHeight = BACkGROUND_IMAGE_WIDTH / aspectRatio
            backgroundImage.viewport =
                Rectangle2D(0.0, (BACKGROUND_IMAGE_HEIGHT - visibleHeight) * 0.5, BACkGROUND_IMAGE_WIDTH, visibleHeight)
        }
        backgroundImage.fitWidth = width
        backgroundImage.fitHeight = height
    }
}
