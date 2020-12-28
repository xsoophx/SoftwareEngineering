package de.tuchemnitz.se.exercise.core.graphics.codecharts


import de.tuchemnitz.se.exercise.codecharts.CodeChartsTool.Companion.ccData
import de.tuchemnitz.se.exercise.codecharts.CodeChartsTool.Companion.handleStrings
import de.tuchemnitz.se.exercise.codecharts.StringCharacters
import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.paint.Color.WHITESMOKE
import tornadofx.View
import tornadofx.datagrid
import tornadofx.hbox
import tornadofx.label
import tornadofx.rectangle
import tornadofx.stackpane

class CodeChartsGridView(
    // private val cssRule: CssRule = Style.ccGridWrapper,
) : View("SoftwarePraktikum - CodeCharts Grid") {
    private val gridWidth = ccData.getGridDimension().x
    private val gridHeight = ccData.getGridDimension().y
    private val gridSize = (gridWidth * gridHeight).toInt()
    private val allowedCharacters = ccData.getAllowedChars()
    private val stringList = getStringList(gridSize, allowedCharacters)
    private val scaledImageSize = ccData.getScaledImageSize()


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
                minWidth = ccData.getScaledImageSize().x + 2
                maxWidth = ccData.getScaledImageSize().x + 2

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

    private fun getStringList(gridSize: Int, allowedCharacters: StringCharacters): MutableList<String> {
        handleStrings.setStrings(input = gridSize, allowedChars = allowedCharacters)
        return handleStrings.getStrings()
    }
}
