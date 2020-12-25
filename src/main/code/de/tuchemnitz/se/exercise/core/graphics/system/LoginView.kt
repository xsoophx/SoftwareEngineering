package de.tuchemnitz.se.exercise.core.graphics.system

import de.tuchemnitz.se.exercise.core.graphics.MainApp
import javafx.scene.control.Button
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import org.slf4j.LoggerFactory
import tornadofx.View
import tornadofx.action
import tornadofx.button
import tornadofx.gridpane
import tornadofx.hbox
import tornadofx.label
import tornadofx.passwordfield
import tornadofx.row
import tornadofx.singleAssign
import tornadofx.textfield

class LoginView : View("Software Praktikum - Gruppe 4") {
  override val root: BorderPane by fxml("/views/MainViewTemplate.fxml")

  companion object {
    val logger = LoggerFactory.getLogger(LoginView::class.java)
  }

  val contentBox: VBox by fxid("content")
  var loginEmailField: TextField by singleAssign()
  var loginPasswordField: PasswordField by singleAssign()

  init {
    with(contentBox) {
      gridpane {
        row {
          label("E-Mail Address:")
          loginEmailField = textfield()
        }
        row {
          label("Password:")
          loginPasswordField = passwordfield()
        }
      }

      hbox(15) {
        button("go back") {
          action {
            logger.info("Return Button pressed")
          }
        }
        button("register") {
          action {
            replaceWith(RegisterView::class)
          }
        }
        button("login") {
          action {
            logger.info("Email: " + loginEmailField.text)
            logger.info("PW: " + loginPasswordField.text)
          }
        }
      }
    }
  }

  fun printGitButton() {
    logger.info(contentBox.width.toString())
  }

  override fun onDock() {
    logger.info("Docking Login Page!")
  }

  override fun onUndock() {
    logger.info("Undocking Login Page!")
  }
}