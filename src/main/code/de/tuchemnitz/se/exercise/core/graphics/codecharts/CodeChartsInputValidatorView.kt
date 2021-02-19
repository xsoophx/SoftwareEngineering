package de.tuchemnitz.se.exercise.core.graphics.codecharts

import de.tuchemnitz.se.exercise.codecharts.CodeChartsConfigMapper
import de.tuchemnitz.se.exercise.codecharts.CodeChartsTool.codeChartsClickCounter
import de.tuchemnitz.se.exercise.codecharts.CodeChartsTool.codeChartsData
import de.tuchemnitz.se.exercise.codecharts.CodeChartsTool.codeChartsStringHandler
import de.tuchemnitz.se.exercise.codecharts.Interval2D
import de.tuchemnitz.se.exercise.core.graphics.system.MainBarView
import javafx.geometry.Point2D
import javafx.geometry.Pos
import javafx.geometry.Rectangle2D
import javafx.scene.control.TextField
import javafx.scene.image.ImageView
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
     * If the picture is already zoomed in it will use the viewport dimensions instead of the original scaled image dimensions
     */
    private fun calculateEyePosition(userInput: String) {
        val listPosition = codeChartsStringHandler.getStrings().indexOf(userInput)
        val xFieldNumber = listPosition % (codeChartsData.gridDimension.x)
        val yFieldNumber = (listPosition / (codeChartsData.gridDimension.y).toInt())
        val cellWidth: Double
        val cellHeight: Double

        if (codeChartsClickCounter.recursionCounter == 0) {
            cellWidth = codeChartsData.scaledImageSize.x / codeChartsData.gridDimension.x
            cellHeight = codeChartsData.scaledImageSize.y / codeChartsData.gridDimension.y
        } else {
            cellWidth = codeChartsClickCounter.viewPort.width / codeChartsData.gridDimension.x
            cellHeight = codeChartsClickCounter.viewPort.height / codeChartsData.gridDimension.y
        }
        val xMinPos = xFieldNumber * cellWidth
        val yMinPos = yFieldNumber * cellHeight
        val xMaxPos = xMinPos + cellWidth
        val yMaxPos = yMinPos + cellHeight

        // interval will later be used for data analysis
        val minMaxEyePos = Interval2D(minimum = Point2D(xMinPos, yMinPos), maximum = Point2D(xMaxPos, yMaxPos))
        val eyePos = if (codeChartsClickCounter.recursionCounter > 0) {
            Interval2D(
                minimum = Point2D(
                    minMaxEyePos.minimum.x + codeChartsClickCounter.viewPort.minX,
                    minMaxEyePos.minimum.y + codeChartsClickCounter.viewPort.minY
                ),
                maximum = Point2D(
                    minMaxEyePos.maximum.x + codeChartsClickCounter.viewPort.minX,
                    minMaxEyePos.maximum.y + codeChartsClickCounter.viewPort.minY
                )
            )
        } else minMaxEyePos

        codeChartsData.eyePos = eyePos

        logger.info("${codeChartsData.eyePos.minimum.x}, ${codeChartsData.eyePos.maximum.x}, ${codeChartsData.eyePos.minimum.y}, ${codeChartsData.eyePos.maximum.y}")
        logger.info("${codeChartsData.scaledImageSize.x}, ${codeChartsData.scaledImageSize.y}")
    }

    /**
     * Adds one to the cell containing the  [userInput]
     */
    private fun calculateRecursionCounter(userInput: String) {
        logger.info("$codeChartsClickCounter.clickList")
        val listPosition = codeChartsStringHandler.getStrings().indexOf(userInput)
        ++codeChartsClickCounter.clickList[listPosition]
        logger.info("$codeChartsClickCounter.clickList")
    }

    /**
     * Checks whether input provided by the user is valid.
     * Replaces CodeChartsInputValidatorView with either CodeChartsThankfulView if the input is valid or
     * with the CodeChartsRetryView otherwise.
     * Saves input to database if it is valid.
     * Checks whether the relative feature is active and if so it checks whether all conditions to zoom into the picture are met
     * If so, the previous viewport will be replaced with the new one
     */
    private fun validateInput() {
        val userInput = inputString.text
        if (codeChartsStringHandler.getStrings().contains(userInput)) {

            calculateEyePosition(userInput)
            calculateRecursionCounter(userInput)
            CodeChartsConfigMapper().saveCodeChartsDatabaseConfig(codeChartsData)
            replaceWith(CodeChartsThankfulView::class)
            inputString.text = ""

            if (codeChartsClickCounter.clickList.contains(codeChartsClickCounter.recurseAt) && codeChartsData.recursionDepth > codeChartsClickCounter.recursionCounter && codeChartsData.relative) {
                print(codeChartsData.eyePos)
                codeChartsClickCounter.viewPort = intervalToRectangle(codeChartsData.eyePos)
                codeChartsClickCounter.pictureImageView.replaceViewPort(codeChartsClickCounter.viewPort)
                codeChartsClickCounter.clickList =
                    MutableList((codeChartsData.gridDimension.x * codeChartsData.gridDimension.y).toInt()) { 0 }
                logger.info("$codeChartsClickCounter.clickList")
                ++codeChartsClickCounter.recursionCounter
            }
        } else {
            replaceWith(CodeChartsRetryView::class)
            inputString.text = ""
        }
    }

    /**
     * replaces the previous viewport of the picture with [zoomedView]
     */
    private fun ImageView.replaceViewPort(zoomedView: Rectangle2D) {
        val factorX = image.width / codeChartsData.scaledImageSize.x
        val factorY = image.height / codeChartsData.scaledImageSize.y
        val newViewPortScaled = Rectangle2D(
            zoomedView.minX * factorX,
            zoomedView.minY * factorY,
            zoomedView.width * factorX,
            zoomedView.height * factorY
        )
        viewport = newViewPortScaled
    }

    /**
     * converts [interval] of type Interval2D to Rectangle2D
     */
    private fun intervalToRectangle(interval: Interval2D): Rectangle2D {
        return Rectangle2D(
            interval.minimum.x,
            interval.minimum.y,
            interval.maximum.x - interval.minimum.x,
            interval.maximum.y - interval.minimum.y
        )
    }
}
