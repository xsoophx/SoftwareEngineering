package de.tuchemnitz.se.exercise.core.graphics.codecharts

import javafx.scene.paint.Color
// import tornadofx.CssRule
import tornadofx.View
import tornadofx.circle
import tornadofx.datagrid
import tornadofx.label
import tornadofx.stackpane
import tornadofx.vbox

class CCGridView(
    // private val cssRule: CssRule = CCStyle.ccGridWrapper,
) : View("SoftwarePraktikum - CodeCharts Grid") {
    override val root = vbox {
        val numbers = (1..10).toList()

        datagrid(numbers) {
            cellHeight = 75.0
            cellWidth = 75.0

            multiSelect = true

            cellCache {
                stackpane {
                    circle(radius = 25.0) {
                        fill = Color.LIMEGREEN
                    }
                    label(it.toString())
                }
            }
        }
    }
}
