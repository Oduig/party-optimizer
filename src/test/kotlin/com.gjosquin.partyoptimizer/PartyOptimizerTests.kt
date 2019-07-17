package com.gjosquin.partyoptimizer

import com.gjosquin.partyoptimizer.definitions.WowClass.Companion.Mage
import com.gjosquin.partyoptimizer.definitions.WowClass.Companion.Priest
import com.gjosquin.partyoptimizer.definitions.WowClass.Companion.Shaman
import com.gjosquin.partyoptimizer.definitions.WowClass.Companion.Warlock
import com.gjosquin.partyoptimizer.definitions.WowClass.Companion.Warrior
import org.junit.Test
import kotlin.test.assertTrue

class PartyOptimizerTests {
    @Test
    fun viableGroupsOfSizeThree() {
        // Arrange
        val groupSize = 3

        // Act
        val viableGroups = PartyOptimizer.viableGroupsOfSize(groupSize)

        // Assert
        val expectedGroups = listOf(
                listOf(Warrior, Priest, Shaman),
                listOf(Warrior, Shaman, Mage),
                listOf(Warrior, Shaman, Warlock)
        )
        expectedGroups.forEach { group ->
            assertTrue(viableGroups.contains(group))
        }
    }
}
