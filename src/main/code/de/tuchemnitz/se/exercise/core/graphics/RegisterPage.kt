package de.tuchemnitz.se.exercise.core.graphics

import javafx.collections.FXCollections
import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.TextAlignment
import tornadofx.Stylesheet.Companion.text
import tornadofx.View
import tornadofx.action
import tornadofx.addClass
import tornadofx.borderpane
import tornadofx.button
import tornadofx.choicebox
import tornadofx.field
import tornadofx.fieldset
import tornadofx.form
import tornadofx.hbox
import tornadofx.label
import tornadofx.passwordfield
import tornadofx.style
import tornadofx.text
import tornadofx.textfield
import tornadofx.textflow
import tornadofx.tooltip
import tornadofx.useMaxWidth
import tornadofx.vbox
import java.awt.SystemColor.text

class RegisterPage : View("Software Praktikum - Gruppe 4") {
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

      center = form {
        fieldset("Registration") {
          field("First Name"){
            textfield()
          }
          field("Last Name"){
            textfield()
          }
          field("E-Mail address:") {
            textfield()
          }
          field("Password:") {
            passwordfield()
          }
          field("Sex:"){
            choicebox<String>{
              items = FXCollections.observableArrayList("Male", "Female", "Other")
            }
          }
        }
        button("register") {
          action {
            println("Button Pressed!")
          }
        }
        button("go back") {
          action {
            replaceWith(LoginPage::class)
            println("Button Pressed!")
          }
        }
        addClass(Style.mainCenterStyle)
      }
    }
  }

  override fun onDock() {
    println("Docking Register Page!")
  }

  override fun onUndock() {
    println("Undocking Register Page!")
  }
}