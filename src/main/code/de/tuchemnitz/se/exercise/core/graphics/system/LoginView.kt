package de.tuchemnitz.se.exercise.core.graphics.system

import de.tuchemnitz.se.exercise.core.graphics.MainApp
import javafx.scene.layout.BorderPane
import org.slf4j.LoggerFactory
import tornadofx.View

class LoginView : View("Software Praktikum - Gruppe 4 - Login") {
    private val logger = LoggerFactory.getLogger(this::class.java)
    override val root: BorderPane by fxml(MainApp.MAIN_VIEW_TEMPLATE_PATH)

    override fun onDock() {
        logger.info("Dock LoginView...")
    }

    override fun onUndock() {
        logger.info("Undock LoginView...")
    }
}
