package de.tuchemnitz.se.exercise.persist.collections

import com.mongodb.client.MongoDatabase
import de.tuchemnitz.se.exercise.persist.configs.ZoomMapsConfig
import org.litote.kmongo.getCollection

class ZoomMapsConfigCollection(
    private val db: MongoDatabase
) : AbstractCollection<ZoomMapsConfig>(collection = db.getCollection())
