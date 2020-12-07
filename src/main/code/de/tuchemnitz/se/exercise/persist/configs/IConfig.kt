package de.tuchemnitz.se.exercise.persist.configs

import de.tuchemnitz.se.exercise.persist.IPersist
import org.litote.kmongo.Id

interface IConfig: IPersist {
  val _id: Id<out IConfig>
}