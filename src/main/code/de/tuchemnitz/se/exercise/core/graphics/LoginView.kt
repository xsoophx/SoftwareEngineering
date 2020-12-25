package de.tuchemnitz.se.exercise.core.graphics

import javafx.scene.control.Button
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.Text
import javafx.scene.text.TextAlignment
import org.litote.kmongo.util.idValue
import org.slf4j.LoggerFactory
import tornadofx.Stylesheet.Companion.content
import tornadofx.Stylesheet.Companion.label
import tornadofx.Stylesheet.Companion.text
import tornadofx.Stylesheet.Companion.textField
import tornadofx.View
import tornadofx.action
import tornadofx.addClass
import tornadofx.borderpane
import tornadofx.button
import tornadofx.field
import tornadofx.fieldset
import tornadofx.form
import tornadofx.getChildList
import tornadofx.gridpane
import tornadofx.gridpaneConstraints
import tornadofx.hbox
import tornadofx.label
import tornadofx.passwordfield
import tornadofx.row
import tornadofx.singleAssign
import tornadofx.style
import tornadofx.text
import tornadofx.textfield
import tornadofx.textflow
import tornadofx.tooltip
import tornadofx.useMaxWidth
import tornadofx.vbox
import java.awt.SystemColor.text
import java.util.logging.Logger

class LoginView : View("Software Praktikum - Gruppe 4") {
  override val root: VBox by fxml("/views/MainViewTemplate.fxml")

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
            replaceWith(MainPageView::class)
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