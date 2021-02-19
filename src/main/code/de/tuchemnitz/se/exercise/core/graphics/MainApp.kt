package de.tuchemnitz.se.exercise.core.graphics

import de.tuchemnitz.se.exercise.core.configmanager.ConfigManager
import de.tuchemnitz.se.exercise.core.graphics.system.StartupView
import javafx.scene.control.Alert
import javafx.stage.Stage
import tornadofx.App
import tornadofx.importStylesheet
import java.io.IOException

class MainApp : App(primaryView = StartupView::class) {

    private val configManager: ConfigManager by inject()

    companion object {
        const val MAIN_VIEW_TEMPLATE_PATH = "/views/MainViewTemplate.fxml"
    }

    override fun start(stage: Stage) {
        stage.isFullScreen = configManager.decodeConfig()?.general?.fullscreen ?: true
        stage.minHeight = 850.0
        stage.minWidth = 1300.0
        super.start(stage)
    }

    init {
        importStylesheet(Style::class)
        try {
            configManager.checkTemplateImages()
        } catch (e: IOException) {
            Alert(Alert.AlertType.INFORMATION).apply {
                headerText = "Some template images are missing. + ${e.message}."
            }.showAndWait()
        }
    }
}
