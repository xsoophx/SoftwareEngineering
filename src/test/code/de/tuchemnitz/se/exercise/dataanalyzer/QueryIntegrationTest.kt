package de.tuchemnitz.se.exercise.dataanalyzer

import assertk.assertThat
import assertk.assertions.containsOnly
import de.tuchemnitz.se.exercise.DummyData
import de.tuchemnitz.se.exercise.persist.configs.collections.CodeChartsConfigCollection
import de.tuchemnitz.se.exercise.persist.data.collections.UserDataCollection
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.litote.kmongo.eq
import tornadofx.Controller

class QueryIntegrationTest : Controller() {

    private val codeChartsConfigCollection: CodeChartsConfigCollection by inject()
    private val userDataCollection: UserDataCollection by inject()

    companion object {
        val query = Query()
        private val dataFilters = setOf(
            UserDataFilter(
                firstName = Filter(taken = true, value = "Klaus"),
                surName = Filter(taken = false, value= ""),
                age = Filter(taken = false, value = -1)
            )
        )
        val userFilter = Query.UserFilter(
            userDataFilter = Filter(taken = true, value = dataFilters.first()),
            codeChartsDataFilter = Filter(taken = false, value = DummyData.codeChartsData.first())
        )
    }

    @BeforeEach
    fun setup() {
        codeChartsConfigCollection.saveOne(DummyData.codeChartsConfigs.first())
        userDataCollection.saveMany(DummyData.userData)
    }

    @AfterEach
    fun close() {
        codeChartsConfigCollection.deleteMany()
        userDataCollection.deleteMany()
    }

    @Test
    fun `querying database with given params should work`() {
        assertThat(userDataCollection.find(DummyData.userData.first()::firstName eq "Klaus")).containsOnly(
            query.queryAllElementsSeparately(userFilter)
        )
    }
}
