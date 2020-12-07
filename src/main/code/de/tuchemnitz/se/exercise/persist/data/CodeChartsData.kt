package de.tuchemnitz.se.exercise.persist.data

import org.litote.kmongo.Id

data class CodeChartsData(
  override val _id: Id<out IData>,
  val dummyData: String
) : IData