package com.gjosquin.partyoptimizer

import com.gjosquin.partyoptimizer.definitions.Constraints
import com.gjosquin.partyoptimizer.definitions.Faction
import com.gjosquin.partyoptimizer.definitions.WowClass

object PartyOptimizer {

    private val constraints = setOf(
            Constraints.groupHasATank,
            Constraints.groupHasAHealer,
            Constraints.groupHasAHealerWhoIsNotTheOnlyTank,
            Constraints.groupHasDifferentArmorClasses,
            Constraints.isFaction(Faction.HORDE),
            Constraints.groupHasA(WowClass.Shaman)
    )

    fun viableGroupsOfSize(groupSize: Int): List<List<WowClass>> {

        return allGroupsOfSize(groupSize).filter { group ->
            constraints.all { constraint -> constraint(group) }
        }.toList()
    }

    private fun allGroupsOfSize(n: Int): Set<List<WowClass>> {
        if (n <= 0) return emptySet()
        else if (n == 1) return WowClass.all.map { listOf(it) }.toSet()

        val smallerGroups = allGroupsOfSize(n - 1)
        val allGroups = WowClass.all.flatMap { classToAdd ->
            smallerGroups.map { smallerGroup ->
                (smallerGroup + classToAdd).sortedWith(WowClass.ordering)
            }
        }.toSet()
        return allGroups
    }
}
