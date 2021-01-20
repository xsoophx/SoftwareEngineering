package de.tuchemnitz.se.exercise.persist.data

import de.tuchemnitz.se.exercise.dataanalyzer.Gender
import org.litote.kmongo.Id
import org.litote.kmongo.newId

data class UserData(
    override val _id: Id<UserData> = newId(),
    val firstName: String,
    val lastName: String,
    val age: Int,
    val gender: Gender
) : IData
