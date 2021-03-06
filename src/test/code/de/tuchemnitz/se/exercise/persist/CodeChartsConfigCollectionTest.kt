package de.tuchemnitz.se.exercise.persist

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.containsOnly
import assertk.assertions.doesNotContain
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import com.mongodb.client.model.Filters
import de.tuchemnitz.se.exercise.DATABASE
import de.tuchemnitz.se.exercise.DummyData
import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import de.tuchemnitz.se.exercise.persist.configs.collections.CodeChartsConfigCollection
import org.bson.conversions.Bson
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.litote.kmongo.`in`
import org.litote.kmongo.eq
import tornadofx.Controller

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@Tag(DATABASE)
class CodeChartsConfigCollectionTest : Controller() {
    private val codeChartsConfigCollection: CodeChartsConfigCollection by inject()

    companion object {
        private val configs = DummyData.codeChartsConfigs
    }

    @AfterEach
    fun cleanUp() {
        codeChartsConfigCollection.deleteMany()
    }

    @Test
    fun `all configs should be saved properly at once`() {
        codeChartsConfigCollection.saveMany(configs)
        assertThat(codeChartsConfigCollection.find(CodeChartsConfig::_id `in` configs.map(CodeChartsConfig::_id)))
            .containsOnly(*configs.toTypedArray())
    }

    @Test
    fun `every config should be saved properly`() {
        configs.forEach {
            codeChartsConfigCollection.saveOne(it)
            assertThat(codeChartsConfigCollection.find(CodeChartsConfig::_id eq it._id)).contains(it)
        }
    }

    @Test
    fun `every config should be deleted properly`() {
        configs.forEach {
            codeChartsConfigCollection.deleteOne(CodeChartsConfig::_id eq it._id)
            assertThat(codeChartsConfigCollection.find(CodeChartsConfig::_id eq it._id)).doesNotContain(it)
        }
    }

    @Test
    fun `findOneById should work properly for each id`() {
        codeChartsConfigCollection.saveMany(configs)
        configs.forEach {
            assertThat(codeChartsConfigCollection.findOneById(it._id)).isEqualTo(it)
        }
    }

    @Test
    fun `configs should be deleted properly at once`() {
        val ids = "_id" `in` configs
        codeChartsConfigCollection.deleteMany(ids)

        configs.forEach {
            codeChartsConfigCollection.deleteOne(CodeChartsConfig::_id eq it._id)
            assertThat(codeChartsConfigCollection.findOneById(it._id)).isNull()
        }
    }

    private infix fun String.`in`(collection: Collection<Any>): Bson =
        Filters.`in`(this, collection)
}
