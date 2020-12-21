package de.tuchemnitz.se.exercise.core.graphics

import javafx.scene.control.Button
import javafx.scene.layout.VBox
import tornadofx.View

class MainLoginView : View("Software Praktikum - Gruppe 4 - Login") {
    override val root: VBox by fxml("/views/MainViewTemplate.fxml")

    val gitButton: Button by fxid("git_button")

    init {
        println(gitButton)
    }

    fun printGitButton() {
        println("Test!!!!")
    }
  /*
  override val root = vbox {
      borderpane {
          top = vbox {
              textflow {
                  textAlignment = TextAlignment.CENTER
                  text("Software Praktikum ") {
                      fill = Color.LIMEGREEN
                      font = Font(32.0)
                  }
                  text("Gruppe 4") {
                      fill = Color.AQUAMARINE
                      font = Font(24.0)
                  }
              }
              addClass(Style.mainTopStyle)
          }

          bottom = hbox(15) {
              button("Our GitHub") {
                  tooltip("Link to GitHub")
                  action {
                      println("Button pressed!")
                  }
              }
              button("Help") {
                  tooltip("Get assistance")
                  action {
                      println("Button pressed!")
                  }
              }
              addClass(Style.mainBottomStyle)
              children.asSequence()
                  .filter { it is Button }
                  .forEach { it.addClass(Style.mainBottomButtonStyle) }
          }

    /*
    left = vbox {

    }
     */

    /*
    right = vbox {

    }
     */

          center = vbox {
              button("Login") {
                  tooltip("Login Button")
                  action {
                      replaceWith(MainPageView::class)
                  }
              }
              addClass(Style.mainCenterStyle)
          }
      }
  }

  override fun onDock() {
      println("Docking Main Login Page!")
  }

  override fun onUndock() {
      println("Undocking Main Login Page!")
  }

   */
}
