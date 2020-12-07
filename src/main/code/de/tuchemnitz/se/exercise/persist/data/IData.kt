package de.tuchemnitz.se.exercise.persist.data

import de.tuchemnitz.se.exercise.persist.IPersist
import org.litote.kmongo.Id

interface IData : IPersist {
    val _id: Id<out IData>
}
