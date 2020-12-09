package de.tuchemnitz.se.exercise.persist.configs.collections

import com.mongodb.client.MongoDatabase
import de.tuchemnitz.se.exercise.persist.AbstractCollection
import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import org.litote.kmongo.getCollection

class CodeChartsConfigCollection(
    db: MongoDatabase
) : AbstractCollection<CodeChartsConfig>(collection = db.getCollection())
