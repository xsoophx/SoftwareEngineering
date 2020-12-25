package de.tuchemnitz.se.exercise.core.graphics

import javafx.collections.FXCollections
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.layout.VBox
import org.slf4j.LoggerFactory
import tornadofx.View
import tornadofx.action
import tornadofx.addClass
import tornadofx.button
import tornadofx.choicebox
import tornadofx.field
import tornadofx.fieldset
import tornadofx.form
import tornadofx.gridpane
import tornadofx.hbox
import tornadofx.label
import tornadofx.passwordfield
import tornadofx.row
import tornadofx.singleAssign
import tornadofx.textfield

class RegisterView : View("Software Praktikum - Gruppe 4") {
  companion object {
    val logger = LoggerFactory.getLogger(RegisterView::class.java)
  }

  override val root: VBox by fxml("/views/MainViewTemplate.fxml")

  val contentBox: VBox by fxid("content")
  var registerEmailField: TextField by singleAssign()
  var registerFirstNameField: TextField by singleAssign()
  var registerLastNameField: TextField by singleAssign()
  var registerPasswordField: PasswordField by singleAssign()

  init {
    with(contentBox) {
      gridpane {
        row {
          label("First Name: ")
          registerFirstNameField = textfield()
        }
        row {
          label("Last Name: ")
          registerLastNameField = textfield()
        }
        row {
          label("E-Mail Address:")
          registerEmailField = textfield()
        }
        row {
          label("Password:")
          registerPasswordField = passwordfield()
        }
      }

      hbox(15) {
        button("go back") {
          action {
            replaceWith(LoginView::class)
          }
        }
        button("register") {
          action {
            logger.info("First Name: " + registerFirstNameField.text + "\nLast Name: " + registerLastNameField.text + "\nEmail: " + registerEmailField.text + "\n Password: " + registerPasswordField.text)
          }
        }
      }
    }
  }

  fun printGitButton() {
    logger.info("irgendwas rein")
  }

  override fun onDock() {
    logger.info("Docking Register Page!")
  }

  override fun onUndock() {
    logger.info("Undocking Register Page!")
  }
}