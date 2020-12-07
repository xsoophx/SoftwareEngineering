package de.tuchemnitz.se.exercise.persist.data

import org.litote.kmongo.Id

data class UserData(
  override val _id: Id<out IData>,
  val firstName: String,
  val surName: String,
  val age: Int
) : IData