package de.tuchemnitz.se.exercise.core.graphics.codecharts

import de.tuchemnitz.se.exercise.codecharts.CodeChartsDataValues
import javafx.scene.paint.Color
import javafx.scene.paint.Color.LIMEGREEN
import javafx.scene.paint.Color.WHITE
import javafx.scene.paint.Color.WHITESMOKE
import tornadofx.View
import tornadofx.circle
import tornadofx.datagrid
import tornadofx.label
import tornadofx.rectangle
import tornadofx.stackpane
import tornadofx.vbox
import javax.swing.GroupLayout

class CodeChartsGridView(
    // private val cssRule: CssRule = Style.ccGridWrapper,
) : View("SoftwarePraktikum - CodeCharts Grid") {
    val numbers = (1..10).toList()
    override val root = datagrid(numbers) {
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
}
