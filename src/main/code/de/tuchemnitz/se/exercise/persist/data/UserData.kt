package de.tuchemnitz.se.exercise.persist.data

import org.litote.kmongo.Id
import org.litote.kmongo.newId

enum class Gender {
    Diverse,
    Male,
    Female,
    Unselected
}

enum class VisionImpaired {
    Yes,
    No,
    Unselected
}

data class UserData(
    override val _id: Id<UserData> = newId(),
    val firstName: String = "default",
    val lastName: String = "default",
    val age: Int = 0,
    val gender: Gender = Gender.Unselected,
    val visionImpaired: VisionImpaired = VisionImpaired.Unselected,
    val default: Boolean
) : IData
