package de.tuchemnitz.se.exercise.core.graphics.codecharts

import de.tuchemnitz.se.exercise.codecharts.CodeChartsTool.codeChartsData
import de.tuchemnitz.se.exercise.codecharts.CodeChartsTool.codeChartsStringHandler
import javafx.animation.PauseTransition
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.paint.Color.WHITESMOKE
import javafx.util.Duration
import tornadofx.View
import tornadofx.datagrid
import tornadofx.hbox
import tornadofx.label
import tornadofx.rectangle
import tornadofx.stackpane

/**
 * Shows grid with generated strings, where user has to remember the String he looked at.
 * Is replaced with CodeChartsInputValidatorView after delay.
 */
class CodeChartsGridView : View("SoftwarePraktikum - CodeCharts Grid") {
    private val gridWidth = codeChartsData.gridDimension.x
    private val gridHeight = codeChartsData.gridDimension.y
    private val stringList = codeChartsStringHandler.getStrings()
    private val scaledImageSize = codeChartsData.scaledImageSize

    override val root =
        hbox {
            alignment = Pos.TOP_CENTER
            datagrid(stringList) {
                cellWidth = scaledImageSize.x / gridWidth
                cellHeight = scaledImageSize.y / gridHeight
                horizontalCellSpacing = 0.0
                verticalCellSpacing = 0.0
                maxCellsInRow = gridWidth.toInt()
                maxRows = gridHeight.toInt()
                minWidth = codeChartsData.scaledImageSize.x + 2.0
                maxWidth = codeChartsData.scaledImageSize.x + 2.0
                cellCache {
                    stackpane {
                        rectangle(width = cellWidth - 0.5, height = cellHeight - 0.5) {
                            stroke = Color.MEDIUMAQUAMARINE
                            fill = WHITESMOKE
                        }
                        label(it)
                    }
                }
            }
        }

    /**
     * Calls a timer. Replaces current view with CodeChartsInputValidatorView after delay.
     */
    private fun goToInputValidatorView() {
        val delay = PauseTransition(Duration.seconds(codeChartsData.matrixViewTime))
        delay.onFinished = EventHandler { replaceWith(CodeChartsInputValidatorView::class) }
        delay.play()
    }

    override fun onDock() {
        goToInputValidatorView()
    }
}
