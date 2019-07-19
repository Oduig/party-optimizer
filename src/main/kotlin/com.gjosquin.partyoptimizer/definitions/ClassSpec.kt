package com.gjosquin.partyoptimizer.definitions

data class ClassSpec(
        val wowClass: WowClass,
        val name: String,
        val statPriority: List<GearStat>,
        val canTank: Boolean,
        val canHeal: Boolean
)
