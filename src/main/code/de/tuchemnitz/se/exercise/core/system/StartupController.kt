package de.tuchemnitz.se.exercise.core.system

import de.tuchemnitz.se.exercise.persist.data.Gender
import de.tuchemnitz.se.exercise.persist.data.VisionImpaired
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.ItemViewModel
import tornadofx.getValue

class StartupController(
    firstName: String = "",
    surname: String = "",
    age: Int = 0,
    gender: Gender = Gender.Unselected,
    visionImpaired: VisionImpaired = VisionImpaired.Unselected
) {
    val firstNameProperty = SimpleStringProperty(this, "firstName", firstName)
    val firstNameValue: String by firstNameProperty

    val lastNameProperty = SimpleStringProperty(this, "surname", surname)
    val lastNameValue: String by lastNameProperty

    val ageProperty = SimpleIntegerProperty(this, "age", age)
    val ageValue by ageProperty

    val genderProperty = SimpleObjectProperty(this, "gender", gender)
    val genderValue: Gender by genderProperty

    val visionImpairedProperty = SimpleObjectProperty(this, "visionImpaired", visionImpaired)
    val visionImpairedValue: VisionImpaired by visionImpairedProperty
}

class StartupControllerModel(startupController: StartupController = StartupController()) :
    ItemViewModel<StartupController>(startupController) {
    val firstName = bind(StartupController::firstNameProperty)
    val lastName = bind(StartupController::lastNameProperty)
    val age = bind(StartupController::ageProperty)
    val gender = bind(StartupController::genderProperty)
    val visionImpaired = bind(StartupController::visionImpairedProperty)
}
