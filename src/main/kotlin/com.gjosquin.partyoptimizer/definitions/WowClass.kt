package com.gjosquin.partyoptimizer.definitions

data class WowClass(
        val name: String,
        val armorClasses: List<ArmorClass>,
        val canTank: Boolean,
        val canHeal: Boolean,
        val faction: Faction = Faction.BOTH
) {
    constructor(name: String, armorClass: ArmorClass, canTank: Boolean, canHeal: Boolean,
                faction: Faction = Faction.BOTH):
            this(name, listOf(armorClass), canTank, canHeal, faction)

    companion object {
        val Warrior = WowClass("Warrior", ArmorClass.PLATE, canTank = true, canHeal = false)
        val Paladin = WowClass("Paladin", ArmorClass.PLATE, canTank = true, canHeal = true, faction = Faction.ALLIANCE)
        val Shaman = WowClass("Shaman", listOf(ArmorClass.LEATHER, ArmorClass.MAIL), canTank = false, canHeal = true, faction = Faction.HORDE)
        val Hunter = WowClass("Hunter", ArmorClass.MAIL, canTank = false, canHeal = false)
        val Rogue = WowClass("Rogue", ArmorClass.LEATHER, canTank = false, canHeal = false)
        val Druid = WowClass("Druid", ArmorClass.LEATHER, canTank = true, canHeal = true)
        val Warlock = WowClass("Warlock", ArmorClass.CLOTH, canTank = false, canHeal = false)
        val Mage = WowClass("Mage", ArmorClass.CLOTH, canTank = false, canHeal = false)
        val Priest = WowClass("Priest", ArmorClass.CLOTH, canTank = false, canHeal = true)

        val ordering = compareBy<WowClass>(
                { !it.canTank },
                { !it.canHeal },
                { it.name }
        )

        val all = listOf(
                Warrior, Paladin, Shaman, Rogue, Hunter, Druid, Warlock, Mage, Priest
        ).sortedWith(ordering)
    }
}
