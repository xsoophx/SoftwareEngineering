package de.tuchemnitz.se.exercise.core.graphics.codecharts

import de.tuchemnitz.se.exercise.codecharts.CodeChartsTool.Companion.handleStrings
import de.tuchemnitz.se.exercise.core.graphics.MainApp
import de.tuchemnitz.se.exercise.core.graphics.Style
import javafx.geometry.Pos
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.TextAlignment
import tornadofx.CssRule
import tornadofx.View
import tornadofx.action
import tornadofx.button
import tornadofx.label
import tornadofx.singleAssign
import tornadofx.text
import tornadofx.textfield
import tornadofx.vbox

class CodeChartsInputValidatorView(
    private val cssRule: CssRule = Style.ccInputValidatorWrapper
) : View("Willkommen bei CodeCharts!") {
    override val root: BorderPane by fxml(MainApp.MAIN_VIEW_TEMPLATE_PATH)

    val contentBox: VBox by fxid("content")
    var inputString: TextField by singleAssign()
    init {
        with(contentBox) {
            vbox {
                alignment = Pos.CENTER
                text("Welchen String haben Sie gerade als erstes gesehen?") {
                    fill = Color.MEDIUMSPRINGGREEN
                    font = Font(22.0)
                    textAlignment = TextAlignment.CENTER
                }
                vbox{
                    label("Gesehener String:")
                    inputString = textfield()
                }
                button("Abschicken") {
                    action {
                        if(handleStrings.getStrings().contains(inputString.text)) {
                            replaceWith(CodeChartsThankfulView::class)
                        }
                        else {
                            replaceWith(CodeChartsRetryView::class)
                        }
                    }
                }
            }
        }
    }
}
