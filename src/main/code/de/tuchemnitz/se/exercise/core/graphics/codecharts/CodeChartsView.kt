package de.tuchemnitz.se.exercise.core.graphics.codecharts

import de.tuchemnitz.se.exercise.codecharts.CodeChartsTool.codeChartsClickCounter
import de.tuchemnitz.se.exercise.core.graphics.system.MainBarView
import javafx.geometry.Pos
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.paint.Color.BLACK
import javafx.scene.paint.Color.MEDIUMSPRINGGREEN
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.scene.text.TextAlignment
import tornadofx.action
import tornadofx.button
import tornadofx.style
import tornadofx.text
import tornadofx.vbox

/**
 * First view during use of CodeCharts. Allows user to start the tool.
 */
class CodeChartsView : MainBarView("Willkommen bei CodeCharts!") {
    init {
        with(contentBox) {
            vbox {
                spacing = 20.0
                alignment = Pos.CENTER
                text("Willkommen im CodeCharts-Tool") {
                    fill = MEDIUMSPRINGGREEN
                    font = Font(22.0)
                    textAlignment = TextAlignment.CENTER
                }
                text("Betätigen Sie den Button, um zu beginnen.") {
                    fill = BLACK
                    font = Font(15.0)
                    textAlignment = TextAlignment.CENTER
                    style {
                        fontWeight = FontWeight.EXTRA_BOLD
                    }
                }
                button("START") {
                    action {
                        if (codeChartsClickCounter.clickList.contains(codeChartsClickCounter.recurseAt)) {
                            changeImageSettings()
                        }
                        replaceWith(CodeChartsPictureView::class)
                    }
                }
            }
        }
    }
}


