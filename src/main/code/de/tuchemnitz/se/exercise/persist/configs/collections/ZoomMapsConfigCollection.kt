package de.tuchemnitz.se.exercise.persist.configs.collections

import com.mongodb.client.MongoDatabase
import de.tuchemnitz.se.exercise.persist.AbstractCollection
import de.tuchemnitz.se.exercise.persist.configs.ZoomMapsConfig
import org.litote.kmongo.getCollection

class ZoomMapsConfigCollection(
    private val db: MongoDatabase
) : AbstractCollection<ZoomMapsConfig>(collection = db.getCollection())
