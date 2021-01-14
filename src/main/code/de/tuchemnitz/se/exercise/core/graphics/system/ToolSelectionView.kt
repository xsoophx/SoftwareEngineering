package de.tuchemnitz.se.exercise.core.graphics.system

import de.tuchemnitz.se.exercise.core.graphics.MainApp
import de.tuchemnitz.se.exercise.core.graphics.codecharts.CodeChartsView
import de.tuchemnitz.se.exercise.core.graphics.zoommaps.ZoomMapsView
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import javafx.scene.paint.Color.BLACK
import javafx.scene.paint.Color.MEDIUMSPRINGGREEN
import javafx.scene.text.Font
import javafx.scene.text.TextAlignment
import org.slf4j.LoggerFactory
import tornadofx.View
import tornadofx.action
import tornadofx.button
import tornadofx.hbox
import tornadofx.text
import tornadofx.tooltip
import tornadofx.vgrow

class ToolSelectionView : View("Software Praktikum - Gruppe 4 - Tool Selection") {
    private val logger = LoggerFactory.getLogger(this::class.java)
    override val root: BorderPane by fxml(MainApp.MAIN_VIEW_TEMPLATE_PATH)
    private val contentBox: VBox by fxid("content")
    private val exitButton: Button by fxid("exit_button")
    private val mainMenuButton: Button by fxid("main_menu_button")

    init {
        with(contentBox) {
            alignment = Pos.CENTER
            spacing = 64.0
            text("Wählen Sie bitte eins dieser Tools aus!") {
                fill = MEDIUMSPRINGGREEN
                font = Font(22.0)
                textAlignment = TextAlignment.CENTER
            }
            hbox {
                spacing = 42.0
                alignment = Pos.CENTER
                vgrow = Priority.ALWAYS
                button("Code Charts Tool") {
                    textFill = BLACK
                    font = Font(22.0)
                    textAlignment = TextAlignment.CENTER
                    action {
                        replaceWith(CodeChartsView::class)
                    }
                }
                button("Zoom Maps Tool") {
                    textFill = BLACK
                    font = Font(22.0)
                    textAlignment = TextAlignment.CENTER
                    action {
                        replaceWith(ZoomMapsView::class)
                    }
                }
                button("Data Analyzer Tool") {
                    textFill = BLACK
                    font = Font(22.0)
                    textAlignment = TextAlignment.CENTER
                    action {
                        replaceWith(ZoomMapsView::class)
                    }
                }
            }
        }

        with(exitButton) {
            action {
                primaryStage.close()
            }
        }

        with(mainMenuButton) {
            action {
                replaceWith(ToolSelectionView::class)
            }
            tooltip = tooltip("Zurück zur Tool-Auswahl.")
            disableProperty().set(true)
        }
    }

    override fun onDock() {
        logger.info("Dock ToolSelectionView...")
    }

    override fun onUndock() {
        logger.info("Undock ToolSelectionView...")
    }
}
