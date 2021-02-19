package de.tuchemnitz.se.exercise.core.graphics.system

import de.tuchemnitz.se.exercise.core.graphics.codecharts.CodeChartsView
import de.tuchemnitz.se.exercise.core.graphics.dataanalyzer.DataClientInitialView
import de.tuchemnitz.se.exercise.core.graphics.eyetracking.EyeTrackingView
import de.tuchemnitz.se.exercise.core.graphics.zoommaps.ZoomMapsView
import javafx.geometry.Pos
import javafx.scene.layout.Priority
import javafx.scene.paint.Color.BLACK
import javafx.scene.paint.Color.MEDIUMSPRINGGREEN
import javafx.scene.text.Font
import javafx.scene.text.TextAlignment
import org.slf4j.LoggerFactory
import tornadofx.action
import tornadofx.button
import tornadofx.hbox
import tornadofx.text
import tornadofx.tooltip
import tornadofx.vgrow

class ToolSelectionView : MainBarView("Software Praktikum - Gruppe 4 - Tool Selection") {
    private val logger = LoggerFactory.getLogger(this::class.java)

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
                        replaceWith(DataClientInitialView::class)
                    }
                }
                button("Eye Tracking Tool") {
                    textFill = BLACK
                    font = Font(22.0)
                    textAlignment = TextAlignment.CENTER
                    action {
                        replaceWith(EyeTrackingView::class)
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
