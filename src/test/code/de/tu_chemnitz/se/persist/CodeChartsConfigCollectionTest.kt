package de.tu_chemnitz.se.persist

import assertk.assertThat
import de.tu_chemnitz.se.persist.configs.CodeChartsConfig
import org.junit.jupiter.api.Test
import org.litote.kmongo.KMongo
import org.litote.kmongo.eq

class CodeChartsConfigCollectionTest {
  private val client = KMongo.createClient() //get com.mongodb.MongoClient new instance
  private val database = client.getDatabase("test") //normal java driver usage
  private val collection = CodeChartsConfigCollection(database)

  companion object{
    private val configs = setOf<CodeChartsConfig>(
      CodeChartsConfig(grid = Pair(100,200), pictureViewTime = 1, matrixViewTime = 2),
      CodeChartsConfig(grid = Pair(0,0), pictureViewTime = 0, matrixViewTime = 0),
      CodeChartsConfig(grid = Pair(400,400), pictureViewTime = 4, matrixViewTime = 4),
    )
  }

  @Test
  fun `all configs should be saved properly at once`(){
    collection.saveMany(configs)
    configs.forEach {
      assertThat(collection.find(CodeChartsConfig::grid eq it.grid))
    }
  }

  @Test
  fun `every config should be saved properly`(){

  }

}