package de.tuchemnitz.se.exercise.persist.data

import org.litote.kmongo.Id
import org.litote.kmongo.newId

enum class Gender{
    DIVERSE,
    MALE,
    FEMALE
}

data class UserData(
    override val _id: Id<UserData> = newId(),
    val firstName: String = "",
    val surname: String = "",
    val age: Int = 1,
    val gender: Gender = Gender.DIVERSE,
    val visionImpaired: Boolean = false
) : IData
