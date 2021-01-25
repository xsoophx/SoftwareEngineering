package de.tuchemnitz.se.exercise.persist.data

import org.litote.kmongo.Id
import org.litote.kmongo.newId

enum class Gender{
    diverse,
    male,
    female
}

data class UserData(
    override val _id: Id<UserData> = newId(),
    val firstName: String = "",
    val lastName: String = "",
    val age: Int = 1,
    val gender: Gender = Gender.diverse,
    val visionImpaired: Boolean = false
) : IData
