package com.gjosquin.partyoptimizer.definitions

import com.gjosquin.partyoptimizer.definitions.GearStat.*

data class WowClass(
        val name: String,
        val armorClassUntil40: ArmorClass,
        val armorClassAfter40: ArmorClass,
        val canTank: Boolean,
        val canHeal: Boolean,
        val faction: Faction = Faction.BOTH
) {
    constructor(name: String, armorClass: ArmorClass, canTank: Boolean, canHeal: Boolean,
                faction: Faction = Faction.BOTH):
            this(name, armorClass, armorClass, canTank, canHeal, faction)

    @Suppress("MemberVisibilityCanBePrivate")
    companion object {
        val Warrior = WowClass("Warrior", ArmorClass.MAIL, ArmorClass.PLATE, canTank = true, canHeal = false)
        val Paladin = WowClass("Paladin", ArmorClass.MAIL, ArmorClass.PLATE, canTank = true, canHeal = true,
                faction = Faction.ALLIANCE)
        val Shaman = WowClass("Shaman", ArmorClass.LEATHER, ArmorClass.MAIL, canTank = false, canHeal = true,
                faction = Faction.HORDE)
        val Hunter = WowClass("Hunter", ArmorClass.MAIL, canTank = false, canHeal = false)
        val Rogue = WowClass("Rogue", ArmorClass.LEATHER, canTank = false, canHeal = false)
        val Druid = WowClass("Druid", ArmorClass.LEATHER, canTank = true, canHeal = true)
        val Warlock = WowClass("Warlock", ArmorClass.CLOTH, canTank = false, canHeal = false)
        val Mage = WowClass("Mage", ArmorClass.CLOTH, canTank = false, canHeal = false)
        val Priest = WowClass("Priest", ArmorClass.CLOTH, canTank = false, canHeal = true)

        val classOrdering = compareBy<WowClass>(
                { !it.canTank },
                { !it.canHeal },
                { it.name }
        )
        val specOrdering = compareBy<ClassSpec>(
                { !it.canTank },
                { !it.canHeal },
                { it.name }
        )

        val allClasses = listOf(
                Warrior, Paladin, Shaman, Rogue, Hunter, Druid, Warlock, Mage, Priest
        ).sortedWith(classOrdering)

        val allSpecs = listOf(
                ClassSpec(Warrior,"Tank", listOf(STAMINA, AGILITY, STRENGTH), canTank = true, canHeal = false),
                ClassSpec(Warrior,"DPS", listOf(STAMINA, AGILITY, STRENGTH), canTank = true, canHeal = false),
                ClassSpec(Paladin,"Tank", listOf(STAMINA, AGILITY, STRENGTH), canTank = true, canHeal = false),
                ClassSpec(Paladin,"Healer", listOf(INTELLECT, STAMINA, SPIRIT), canTank = false, canHeal = true),
                ClassSpec(Paladin,"DPS", listOf(STRENGTH, AGILITY, INTELLECT), canTank = false, canHeal = false),
                ClassSpec(Shaman, "Melee DPS", listOf(STRENGTH, AGILITY, INTELLECT), canTank = false, canHeal = false),
                ClassSpec(Shaman, "Healer", listOf(INTELLECT, SPIRIT, STAMINA), canTank = false, canHeal = true),
                ClassSpec(Shaman, "Ranged DPS", listOf(INTELLECT, SPIRIT, STAMINA), canTank = false, canHeal = false),
                ClassSpec(Hunter, "DPS", listOf(AGILITY, STAMINA, INTELLECT), canTank = false, canHeal = false),
                ClassSpec(Rogue, "DPS", listOf(AGILITY, SPIRIT, STRENGTH), canTank = false, canHeal = false),
                ClassSpec(Druid, "Tank", listOf(STAMINA, AGILITY, STRENGTH), canTank = true, canHeal = false),
                ClassSpec(Druid, "DPS", listOf(AGILITY, STRENGTH, INTELLECT), canTank = false, canHeal = false),
                ClassSpec(Druid, "Healer", listOf(INTELLECT, SPIRIT, STAMINA), canTank = false, canHeal = true),
                ClassSpec(Warlock, "DPS", listOf(INTELLECT, STAMINA, SPIRIT), canTank = false, canHeal = false),
                ClassSpec(Mage, "DPS", listOf(INTELLECT, STAMINA, SPIRIT), canTank = false, canHeal = false),
                ClassSpec(Priest, "Healer", listOf(SPIRIT, INTELLECT, STAMINA), canTank = false, canHeal = true),
                ClassSpec(Priest, "DPS", listOf(INTELLECT, SPIRIT, STAMINA), canTank = false, canHeal = false)
        ).sortedWith(specOrdering)
    }
}
