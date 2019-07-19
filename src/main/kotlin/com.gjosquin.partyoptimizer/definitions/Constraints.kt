package com.gjosquin.partyoptimizer.definitions

object Constraints {

    val groupHasATank: Constraint = { specs -> specs.any { it.canTank } }

    val groupHasAHealer: Constraint = { specs -> specs.any { it.canHeal } }

    val groupHasAHealerWhoIsNotTheOnlyTank: Constraint = { specs ->
        specs.any {
            val tanks = specs.filter { it.canTank }
            tanks.isNotEmpty() && it.canHeal && (tanks.size > 1 || it != tanks.first())
        }
    }

    val differentGearRequirementsBelow40: Constraint = { specs ->
        val specsByArmorClass = specs.groupBy { it.wowClass.armorClassUntil40 }
        specsByArmorClass.none { it.value.size > 1 && hasOverlappingStatPriorities(it.value) }
    }

    val differentGearRequirementsAfter40: Constraint = { specs ->
        val specsByArmorClass = specs.groupBy { it.wowClass.armorClassAfter40 }
        specsByArmorClass.none { it.value.size > 1 && hasOverlappingStatPriorities(it.value) }
    }

    fun isFaction(faction: Faction): Constraint = { specs ->
        specs.all { it.wowClass.faction == Faction.BOTH || it.wowClass.faction == faction }
    }

    fun groupHasA(wowClass: WowClass, specCondition: (ClassSpec) -> Boolean = { true }): Constraint = { specs ->
        specs.any { it.wowClass == wowClass && specCondition(it) }
    }

    private fun hasOverlappingStatPriorities(specs: List<ClassSpec>): Boolean {
        return GearStats.all.filter {gearStat ->
            specs.count {
                it.statPriority.take(2).contains(gearStat)
            } > 1
        }.any()
    }
}
