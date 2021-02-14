package de.tuchemnitz.se.exercise.persist.data

import org.litote.kmongo.Id
import org.litote.kmongo.newId

enum class Gender {
    Diverse,
    Male,
    Female,
    Unselected
}

data class UserData(
    override val _id: Id<UserData> = newId(),
    val firstName: String,
    val lastName: String,
    val age: Int,
    val gender: Gender,
    val visionImpaired: Boolean
) : IData
