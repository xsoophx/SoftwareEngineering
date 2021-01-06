package de.tuchemnitz.se.exercise.persist

import org.litote.kmongo.Id

interface IPersist {
    val _id: Id<out IPersist>
}
