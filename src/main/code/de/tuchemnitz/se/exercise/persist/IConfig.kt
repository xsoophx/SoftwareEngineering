package de.tuchemnitz.se.exercise.persist

import org.litote.kmongo.Id

interface IConfig {
    val _id: Id<out IConfig>
}
