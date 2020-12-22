package de.tuchemnitz.se.exercise.core.graphics.system

import javafx.scene.control.Button
import javafx.scene.layout.BorderPane
import tornadofx.View

class LoginView : View("Software Praktikum - Gruppe 4 - Login") {
    override val root: BorderPane by fxml("/views/MainViewTemplate.fxml")

    val gitButton: Button by fxid("git_button")

    init {
    }

    fun printGitButton() {
    }

    override fun onDock() {
        // println("Docking Login View!") TODO: logging
    }

    override fun onUndock() {
        // println("Undocking Login View!") TODO: logging
    }
}
