package de.tuchemnitz.se.exercise.dataanalyzer

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.isEqualTo
import de.tuchemnitz.se.exercise.DummyData
import de.tuchemnitz.se.exercise.persist.configs.collections.CodeChartsConfigCollection
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.litote.kmongo.eq
import tornadofx.Controller

class QueryIntegrationTest : Controller() {

    private val codeChartsConfigCollection: CodeChartsConfigCollection by inject()

    companion object {
        val query = Query()
        private val dataFilters = setOf(
            UserDataFilter(
                firstName = Filter(taken = true, value = "Klaus"),
                lastName = Filter(taken = false, value = ""),
                age = Filter(taken = false, value = -1)
            )
        )
        val userFilter = Query.UserFilter(
            userDataFilter = Filter(taken = true, value = dataFilters.first()),
            codeChartsDataFilter = Filter(taken = false, value = DummyData.codeChartsData.first()),
            codeChartsConfigFilter = Filter(taken = false, value = DummyData.codeChartsConfigs.first())
        )
    }

    @BeforeEach
    fun setup() {
        codeChartsConfigCollection.saveOne(DummyData.codeChartsConfigs.first())
        query.userDataCollection.saveMany(DummyData.userData)
    }

    @AfterEach
    fun close() {
        codeChartsConfigCollection.deleteMany()
        query.userDataCollection.deleteMany()
    }

    @Test
    fun `querying database with given params should work`() {
        val queryResult = query.queryAllElementsSeparately(userFilter)
        val databaseIterable = query.userDataCollection.find(DummyData.userData.first()::firstName eq "Klaus")

        databaseIterable.forEach {
            assertThat(queryResult).contains(it)
        }
        assertThat(queryResult.size).isEqualTo(databaseIterable.count())
    }
}
