package de.tu_chemnitz.se.persist

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase
import de.tu_chemnitz.se.persist.configs.CodeChartsConfig
import org.litote.kmongo.getCollection

class CodeChartsConfigCollection(
  val db: MongoDatabase
) : AbstractCollection<CodeChartsConfig>(collection = db.getCollection<CodeChartsConfig>()) {

}