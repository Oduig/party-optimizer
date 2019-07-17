package com.gjosquin.partyoptimizer.definitions

object Constraints {

    val groupHasATank: Constraint = { classes -> classes.any { it.canTank } }

    val groupHasAHealer: Constraint = { classes -> classes.any { it.canHeal } }

    val groupHasAHealerWhoIsNotTheOnlyTank: Constraint = { classes ->
        classes.any {
            val tanks = classes.filter { it.canTank }
            tanks.isNotEmpty() && it.canHeal && (tanks.size > 1 || it != tanks.first())
        }
    }

    val groupHasDifferentArmorClasses: Constraint = { classes ->
        val armorClasses = classes.flatMap { it.armorClasses }
        armorClasses.distinct().size == armorClasses.size
    }

    fun isFaction(faction: Faction): Constraint = { classes ->
        classes.all { it.faction == Faction.BOTH || it.faction == faction }
    }

    fun groupHasA(wowClass: WowClass): Constraint = { classes ->
        classes.any { it == wowClass }
    }
}
