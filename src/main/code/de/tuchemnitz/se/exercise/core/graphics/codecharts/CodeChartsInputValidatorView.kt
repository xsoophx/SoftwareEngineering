package de.tuchemnitz.se.exercise.core.graphics.codecharts

import de.tuchemnitz.se.exercise.codecharts.CodeChartsTool.Companion.ccData
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
                        val userInput = inputString.text
                        if (userInput == "") {} else if (handleStrings.getStrings().contains(userInput)) {
                            calculateEyePosition(userInput)
                            replaceWith(CodeChartsThankfulView::class)
                        } else {
                            replaceWith(CodeChartsRetryView::class)
                        }
                    }
                }
            }
        }
    }
    private fun calculateEyePosition(userInput: String) {
        val listPosition = handleStrings.getStrings().indexOf(userInput)
        val xFieldNumber = listPosition % (ccData.getGridDimension().x)
        val yFieldNumber = (listPosition / (ccData.getGridDimension().y)).toInt()
        val cellWidth = ccData.getScaledImageSize().x / ccData.getGridDimension().x
        val cellHeight = ccData.getScaledImageSize().y / ccData.getGridDimension().y
        val xMinPos = xFieldNumber * cellWidth
        val yMinPos = yFieldNumber * cellHeight
        val xMaxPos = xMinPos + cellWidth
        val yMaxPos = yMinPos + cellHeight

        val eyePos = Interval2D(xMin = xMinPos, xMax = xMaxPos, yMin = yMinPos, yMax = yMaxPos)
        ccData.setEyePos(eyePos)

        println("${ccData.getEyePos().xMin}, ${ccData.getEyePos().xMax}, ${ccData.getEyePos().yMin}, ${ccData.getEyePos().yMax}")
        println("${ccData.getScaledImageSize().x}, ${ccData.getScaledImageSize().y}")
    }
    fun printGitButton() {
    }
}
