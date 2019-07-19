package com.gjosquin.partyoptimizer

import com.gjosquin.partyoptimizer.definitions.ClassSpec
import com.gjosquin.partyoptimizer.definitions.Constraints
import com.gjosquin.partyoptimizer.definitions.Faction
import com.gjosquin.partyoptimizer.definitions.WowClass

object PartyOptimizer {

    private val constraints = setOf(
            Constraints.groupHasATank,
            Constraints.groupHasAHealer,
            Constraints.groupHasAHealerWhoIsNotTheOnlyTank,
            Constraints.differentGearRequirementsBelow40,
            Constraints.differentGearRequirementsAfter40,
            Constraints.isFaction(Faction.HORDE),
            Constraints.groupHasA(WowClass.Shaman) { !it.canHeal }
    )

    fun viableGroupsOfSize(groupSize: Int): List<List<ClassSpec>> {

        return allGroupsOfSize(groupSize).filter { group ->
            constraints.all { constraint -> constraint(group) }
        }.toList()
    }

    private fun allGroupsOfSize(n: Int): Set<List<ClassSpec>> {
        if (n <= 0) return emptySet()
        else if (n == 1) return WowClass.allSpecs.map { listOf(it) }.toSet()

        val smallerGroups = allGroupsOfSize(n - 1)
        val allGroups = WowClass.allSpecs.flatMap { classToAdd ->
            smallerGroups.map { smallerGroup ->
                (smallerGroup + classToAdd).sortedWith(WowClass.specOrdering)
            }
        }.toSet()
        return allGroups
    }
}
