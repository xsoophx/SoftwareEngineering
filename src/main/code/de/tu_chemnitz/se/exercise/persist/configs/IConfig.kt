package de.tu_chemnitz.se.exercise.persist.configs

import de.tu_chemnitz.se.exercise.persist.IPersist
import org.litote.kmongo.Id

interface IConfig: IPersist {
  val _id: Id<out IConfig>
}