package de.tu_chemnitz.se.exercise.persist.data

import de.tu_chemnitz.se.exercise.persist.IPersist
import org.litote.kmongo.Id

interface IData : IPersist {
  val _id: Id<out IData>
}