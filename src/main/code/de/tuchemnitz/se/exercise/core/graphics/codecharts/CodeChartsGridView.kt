package de.tuchemnitz.se.exercise.core.graphics.codecharts


import de.tuchemnitz.se.exercise.codecharts.CodeChartsTool.Companion.ccData
import de.tuchemnitz.se.exercise.codecharts.CodeChartsTool.Companion.handleStrings
import de.tuchemnitz.se.exercise.codecharts.StringCharacters
import javafx.scene.paint.Color
import javafx.scene.paint.Color.WHITESMOKE
import tornadofx.View
import tornadofx.datagrid
import tornadofx.label
import tornadofx.rectangle
import tornadofx.stackpane

class CodeChartsGridView(
    // private val cssRule: CssRule = Style.ccGridWrapper,
) : View("SoftwarePraktikum - CodeCharts Grid") {
    val gridWidth = ccData.getGridDimension().x
    val gridHeight = ccData.getGridDimension().y
    val gridSize = (gridWidth * gridHeight).toInt()
    val allowedCharacters = ccData.getAllowedChars()
    val stringList = getStringList(gridSize, allowedCharacters)

    override val root = datagrid(stringList) {
        cellHeight = 75.0
        cellWidth = 75.0
        horizontalCellSpacing = 0.0
        verticalCellSpacing = 0.0
        multiSelect = true


        cellCache {
            stackpane {
                rectangle(width = cellWidth - 0.5, height = cellHeight - 0.5) {
                    stroke = Color.MEDIUMAQUAMARINE
                    fill = WHITESMOKE
                }
                label(it.toString())
            }
        }
    }

    private fun getStringList(gridSize: Int, allowedCharacters: StringCharacters): MutableList<String> {
        handleStrings.setStrings(input = gridSize, allowedChars = allowedCharacters)
        return handleStrings.getStrings()
    }
}
