package de.tuchemnitz.se.exercise.core.graphics

import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.StackPane
import javafx.stage.Stage
import org.testfx.framework.junit.ApplicationTest

class PictureTest() : ApplicationTest() {

    // TODO: write actual tests

    override fun start(stage: Stage) {
        val button = Button("click me!")
        button.setOnAction { button.text = "clicked!" }
        stage.scene = Scene(StackPane(button), 100.0, 100.0)
        stage.show()
    }
}
