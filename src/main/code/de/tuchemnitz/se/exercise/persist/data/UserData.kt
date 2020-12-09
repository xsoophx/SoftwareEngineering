package de.tuchemnitz.se.exercise.persist.data

import org.litote.kmongo.Id
import org.litote.kmongo.newId

data class UserData(
    override val _id: Id<UserData> = newId(),
    val firstName: String,
    val surName: String,
    val age: Int
) : IData
