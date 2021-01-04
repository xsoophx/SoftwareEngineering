package de.tuchemnitz.se.exercise.core.graphics.codecharts

import de.tuchemnitz.se.exercise.codecharts.CodeChartsTool.Companion.codeChartsData
import de.tuchemnitz.se.exercise.codecharts.CodeChartsTool.Companion.handleStrings
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

class CodeChartsGridView/*(
    private val cssRule: CssRule = Style.ccGridWrapper,
)*/ : View("SoftwarePraktikum - CodeCharts Grid") {
    private val gridWidth = codeChartsData.getGridDimension().x
    private val gridHeight = codeChartsData.getGridDimension().y
    private val stringList = handleStrings.getStrings()
    private val scaledImageSize = codeChartsData.getScaledImageSize()

    override val root =
        hbox {
            alignment = Pos.TOP_CENTER
            datagrid(stringList) {
                // addClass(cssRule)
                cellWidth = scaledImageSize.x / gridWidth
                cellHeight = scaledImageSize.y / gridHeight
                horizontalCellSpacing = 0.0
                verticalCellSpacing = 0.0
                maxCellsInRow = gridWidth.toInt()
                maxRows = gridHeight.toInt()
                minWidth = codeChartsData.getScaledImageSize().x + 2.0
                maxWidth = codeChartsData.getScaledImageSize().x + 2.0
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

    private fun goToInputValidatorView() {
        val delay = PauseTransition(Duration.seconds(codeChartsData.getMatrixViewTime()))
        delay.onFinished = EventHandler { replaceWith(CodeChartsInputValidatorView::class) }
        delay.play()
    }

    override fun onDock() {
        goToInputValidatorView()
    }
}
