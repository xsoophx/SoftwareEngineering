package de.tuchemnitz.se.exercise.core.graphics.codecharts

import de.tuchemnitz.se.exercise.codecharts.CodeChartsTool.Companion.codeChartsData
import de.tuchemnitz.se.exercise.codecharts.CodeChartsTool.Companion.handleStrings
import de.tuchemnitz.se.exercise.codecharts.Interval2D
import de.tuchemnitz.se.exercise.core.graphics.MainApp
import javafx.geometry.Pos
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.TextAlignment
import tornadofx.View
import tornadofx.action
import tornadofx.button
import tornadofx.label
import tornadofx.singleAssign
import tornadofx.text
import tornadofx.textfield
import tornadofx.vbox

/**
 * Asks user to enter the String they just saw and validates it.
 * Calculates eye position according to scaled image size in pixels.
 * Saves collected data.
 * Is replaced after user confirms input by pressing button.
 */
class CodeChartsInputValidatorView/*(
    private val cssRule: CssRule = Style.ccInputValidatorWrapper
)*/ : View("CodeCharts - Eingabe") {
    override val root: BorderPane by fxml(MainApp.MAIN_VIEW_TEMPLATE_PATH)

    private val contentBox: VBox by fxid("content")
    private var inputString: TextField by singleAssign()

    init {
        with(contentBox) {
            vbox {
                alignment = Pos.CENTER
                text("Welchen String haben Sie gerade als erstes gesehen?") {
                    fill = Color.MEDIUMSPRINGGREEN
                    font = Font(22.0)
                    textAlignment = TextAlignment.CENTER
                }
                vbox {
                    label("Gesehener String:")
                    inputString = textfield()
                }
                button("Abschicken") {
                    action {
                        validateInput()
                    }
                }
            }
        }
    }

    private fun calculateEyePosition(userInput: String) {
        val listPosition = handleStrings.getStrings().indexOf(userInput)
        val xFieldNumber = listPosition % (codeChartsData.getGridDimension().x)
        val yFieldNumber = (listPosition / (codeChartsData.getGridDimension().y)).toInt()
        val cellWidth = codeChartsData.getScaledImageSize().x / codeChartsData.getGridDimension().x
        val cellHeight = codeChartsData.getScaledImageSize().y / codeChartsData.getGridDimension().y
        val xMinPos = xFieldNumber * cellWidth
        val yMinPos = yFieldNumber * cellHeight
        val xMaxPos = xMinPos + cellWidth
        val yMaxPos = yMinPos + cellHeight

        // interval will later be used for data analysis
        val eyePos = Interval2D(xMin = xMinPos, xMax = xMaxPos, yMin = yMinPos, yMax = yMaxPos)
        codeChartsData.setEyePos(eyePos)

        println("${codeChartsData.getEyePos().xMin}, ${codeChartsData.getEyePos().xMax}, ${codeChartsData.getEyePos().yMin}, ${codeChartsData.getEyePos().yMax}")
        println("${codeChartsData.getScaledImageSize().x}, ${codeChartsData.getScaledImageSize().y}")
    }

    private fun validateInput() {
        val userInput = inputString.text
        if (userInput == "") {
        } else if (handleStrings.getStrings().contains(userInput)) {
            calculateEyePosition(userInput)
            replaceWith(CodeChartsThankfulView::class)
            inputString.text = ""
        } else {
            replaceWith(CodeChartsRetryView::class)
            inputString.text = ""
        }
    }

    // to be removed
    fun printGitButton() {
    }
}
