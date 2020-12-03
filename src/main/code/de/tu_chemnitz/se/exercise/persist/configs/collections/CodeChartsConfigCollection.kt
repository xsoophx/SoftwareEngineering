package de.tu_chemnitz.se.exercise.persist.configs.collections

import com.mongodb.client.MongoDatabase
import de.tu_chemnitz.se.exercise.persist.AbstractCollection
import de.tu_chemnitz.se.exercise.persist.configs.CodeChartsConfig
import org.litote.kmongo.getCollection

class CodeChartsConfigCollection(
  private val db: MongoDatabase
) : AbstractCollection<CodeChartsConfig>(collection = db.getCollection())