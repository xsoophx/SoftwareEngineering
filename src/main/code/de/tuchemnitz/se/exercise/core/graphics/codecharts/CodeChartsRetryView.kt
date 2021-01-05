package de.tuchemnitz.se.exercise.core.graphics.codecharts

import de.tuchemnitz.se.exercise.core.graphics.MainApp
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
 * Replaces CodeChartsInputValidatorView if input is wrong.
 * User can close program or go back to CodeChartsDialogView to try again.
 */
class CodeChartsRetryView : View("CodeCharts - Ungültige Eingabe") {
    override val root: BorderPane by fxml(MainApp.MAIN_VIEW_TEMPLATE_PATH)

    private val contentBox: VBox by fxid("content")

    init {
        with(contentBox) {
            vbox {
                alignment = Pos.CENTER
                text("Fehlerhafte Eingabe!") {
                    fill = Color.RED
                    font = Font(22.0)
                    textAlignment = TextAlignment.CENTER
                }
                text("Ihre Eingbe stimmt mit keiner der angezeigten Zeichenketten überein.") {
                    fill = Color.BLACK
                    font = Font(15.0)
                    textAlignment = TextAlignment.CENTER
                    style {
                        fontWeight = FontWeight.EXTRA_BOLD
                    }
                }
                text("Möchten Sie es erneut versuchen oder die Umfrage an dieser Stelle beenden?") {
                    fill = Color.BLACK
                    font = Font(15.0)
                    textAlignment = TextAlignment.CENTER
                    style {
                        fontWeight = FontWeight.EXTRA_BOLD
                    }
                }
                hbox {
                    button("Neuer Versuch") {
                        action {
                            replaceWith(CodeChartsDialogView::class)
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

    // to be removed
    fun printGitButton() {
    }
}
