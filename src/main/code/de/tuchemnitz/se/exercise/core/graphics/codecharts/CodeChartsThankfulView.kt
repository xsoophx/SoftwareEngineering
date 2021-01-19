package de.tuchemnitz.se.exercise.core.graphics.codecharts

import de.tuchemnitz.se.exercise.core.graphics.system.MainBarView
import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.scene.text.TextAlignment
import tornadofx.style
import tornadofx.text
import tornadofx.vbox

/**
 * Replaces CodeChartsInputValidatorView if input is correct.
 * User can close program or go back to CodeChartsDialogView to try again.
 */
class CodeChartsThankfulView : MainBarView("Wir sagen DANKE!") {

    init {
        with(contentBox) {
            vbox {
                spacing = 20.0
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
            }
        }
    }
}
