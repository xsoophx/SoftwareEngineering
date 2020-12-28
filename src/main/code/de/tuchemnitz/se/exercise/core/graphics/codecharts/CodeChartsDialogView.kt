package de.tuchemnitz.se.exercise.core.graphics.codecharts

import de.tuchemnitz.se.exercise.core.graphics.MainApp
import de.tuchemnitz.se.exercise.core.graphics.Style
import javafx.geometry.Pos
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color.BLACK
import javafx.scene.paint.Color.MEDIUMSPRINGGREEN
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.scene.text.TextAlignment
import tornadofx.CssRule
import tornadofx.View
import tornadofx.action
import tornadofx.button
import tornadofx.style
import tornadofx.text
import tornadofx.vbox

class CodeChartsDialogView(
    private val cssRule: CssRule = Style.ccDialogWrapper
) : View("Willkommen bei CodeCharts!") {
    override val root: BorderPane by fxml(MainApp.MAIN_VIEW_TEMPLATE_PATH)

    val contentBox: VBox by fxid("content")

    init {
        with(contentBox) {
            vbox {
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
                        replaceWith(CodeChartsPictureView::class)
                    }
                }
            }
        }
    }

    fun printGitButton() {
    }
}
