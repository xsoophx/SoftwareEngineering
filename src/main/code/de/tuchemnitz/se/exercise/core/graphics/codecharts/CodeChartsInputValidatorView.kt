package de.tuchemnitz.se.exercise.core.graphics.codecharts

import de.tuchemnitz.se.exercise.codecharts.CodeChartsConfigMapper
import de.tuchemnitz.se.exercise.codecharts.CodeChartsTool.codeChartsData
import de.tuchemnitz.se.exercise.codecharts.CodeChartsTool.codeChartsStringHandler
import de.tuchemnitz.se.exercise.codecharts.Interval2D
import de.tuchemnitz.se.exercise.core.graphics.system.MainBarView
import de.tuchemnitz.se.exercise.persist.datatool.DataTool
import javafx.geometry.Pos
import javafx.scene.control.TextField
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.TextAlignment
import org.slf4j.LoggerFactory
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
class CodeChartsInputValidatorView : MainBarView("CodeCharts - Eingabe") {
    /**
     * [inputString] is the string that the user enters into the textfield.
     */
    private var inputString: TextField by singleAssign()

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    init {
        with(contentBox) {
            vbox {
                spacing = 20.0
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

    /**
     * Calculates the grid cell the user looked at from the [userInput] given by the user.
     */
    private fun calculateEyePosition(userInput: String) {
        val listPosition = codeChartsStringHandler.getStrings().indexOf(userInput)
        val xFieldNumber = listPosition % (codeChartsData.gridDimension.x)
        val yFieldNumber = (listPosition / (codeChartsData.gridDimension.y).toInt())
        val cellWidth = codeChartsData.scaledImageSize.y / codeChartsData.gridDimension.x
        val cellHeight = codeChartsData.scaledImageSize.y / codeChartsData.gridDimension.y
        val xMinPos = xFieldNumber * cellWidth
        val yMinPos = yFieldNumber * cellHeight
        val xMaxPos = xMinPos + cellWidth
        val yMaxPos = yMinPos + cellHeight

        // interval will later be used for data analysis
        val eyePos = Interval2D(xMin = xMinPos, xMax = xMaxPos, yMin = yMinPos, yMax = yMaxPos)
        codeChartsData.eyePos = eyePos

        logger.info("${codeChartsData.eyePos.xMin}, ${codeChartsData.eyePos.xMax}, ${codeChartsData.eyePos.yMin}, ${codeChartsData.eyePos.yMax}")
        logger.info("${codeChartsData.scaledImageSize.x}, ${codeChartsData.scaledImageSize.y}")
    }

    /**
     * Checks whether input provided by the user is valid.
     * Replaces CodeChartsInputValidatorView with either CodeChartsThankfulView if the input is valid or
     * with the CodeChartsRetryView otherwise.
     * Saves input to database if it is valid.
     */
    private fun validateInput() {
        val userInput = inputString.text
        if (codeChartsStringHandler.getStrings().contains(userInput)) {
            calculateEyePosition(userInput)
            CodeChartsConfigMapper().saveCodeChartsDatabaseConfig(codeChartsData)
            DataTool()
            replaceWith(CodeChartsThankfulView::class)
            inputString.text = ""
        } else {
            replaceWith(CodeChartsRetryView::class)
            inputString.text = ""
        }
    }
}
