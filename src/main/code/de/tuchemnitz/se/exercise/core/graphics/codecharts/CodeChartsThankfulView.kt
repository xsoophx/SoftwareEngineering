package de.tuchemnitz.se.exercise.core.graphics.codecharts

import de.tuchemnitz.se.exercise.codecharts.CodeChartsPictureViewController
import de.tuchemnitz.se.exercise.core.graphics.MainApp
import de.tuchemnitz.se.exercise.core.graphics.system.ToolSelectionView
import javafx.geometry.Pos
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.scene.text.TextAlignment
import tornadofx.View
import tornadofx.action
import tornadofx.button
import tornadofx.hbox
import tornadofx.style
import tornadofx.text
import tornadofx.vbox

/**
 * Replaces CodeChartsInputValidatorView if input is correct.
 * User can close program or go back to CodeChartsDialogView to try again.
 */
class CodeChartsThankfulView/*(
    private val cssRule: CssRule = Style.ccThankfulWrapper
)*/ : View("Wir sagen DANKE!") {
    override val root: BorderPane by fxml(MainApp.MAIN_VIEW_TEMPLATE_PATH)

    private val contentBox: VBox by fxid("content")
    private val codeChartsPictureViewController: CodeChartsPictureViewController by inject()

    init {
        with(contentBox) {
            vbox {
                alignment = Pos.CENTER
                text("Vielen Dank für Ihre Teilnahme!") {
                    fill = Color.MEDIUMSPRINGGREEN
                    font = Font(22.0)
                    textAlignment = TextAlignment.CENTER
                }
                text("Ihre Eingaben wurden gespeichert.") {
                    fill = Color.BLACK
                    font = Font(15.0)
                    textAlignment = TextAlignment.CENTER
                    style {
                        fontWeight = FontWeight.EXTRA_BOLD
                    }
                }
                text("Möchten Sie zum Beginn des Tools zurückkehren oder die Umfrage beenden?") {
                    fill = Color.BLACK
                    font = Font(15.0)
                    textAlignment = TextAlignment.CENTER
                    style {
                        fontWeight = FontWeight.EXTRA_BOLD
                    }
                }
                hbox {
                    button("Hauptmenü") {
                        action {
                            replaceWith(ToolSelectionView::class)
                        }
                    }
                    button("Beenden") {
                        action {
                            primaryStage.close()
                        }
                    }
                }
            }
        }
    }

    fun printGitButton() {
    }
}
