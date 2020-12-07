package de.tuchemnitz.se.exercise.core.graphics

import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.StackPane
import javafx.stage.Stage
import org.junit.jupiter.params.provider.Arguments
import org.testfx.framework.junit.ApplicationTest
import tornadofx.Controller
import java.util.stream.Stream

class PictureTest() : ApplicationTest() {

    companion object {
        @JvmStatic
        @Suppress("unused")
        fun providePaths(): Stream<Arguments> = Stream.of(
            Arguments.of("cat.jpg"),
            Arguments.of("octopus.png"),
            Arguments.of("")
        )
    }

    // private val picture = Picture("", "A Title")
    private lateinit var controller: Controller

    override fun start(stage: Stage) {
        val button = Button("click me!")
        button.setOnAction { button.text = "clicked!" }
        stage.scene = Scene(StackPane(button), 100.0, 100.0)
        stage.show()
    }
}
