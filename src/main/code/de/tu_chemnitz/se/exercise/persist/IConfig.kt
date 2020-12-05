package de.tu_chemnitz.se.exercise.persist

import org.litote.kmongo.Id

interface IConfig {
    val _id: Id<out IConfig>
}
