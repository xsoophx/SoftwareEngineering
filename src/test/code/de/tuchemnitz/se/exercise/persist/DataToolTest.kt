package de.tuchemnitz.se.exercise.persist

import de.tuchemnitz.se.exercise.persist.datatool.DataTool
import org.junit.jupiter.api.Test

class DataToolTest {
    private val dataTool = DataTool()

    @Test
    fun `get recent config of db`(){
        dataTool.dataFile()
    }
}