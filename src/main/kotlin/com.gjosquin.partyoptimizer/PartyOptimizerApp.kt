package com.gjosquin.partyoptimizer

fun main(args: Array<String>) {
    val groupSize = if (args.isEmpty()) 3 else args[0].toInt()
    val viableGroups = PartyOptimizer.viableGroupsOfSize(groupSize)

    viableGroups.forEachIndexed { index, group ->
        val row = group.joinToString { it.name }
        println("$index: $row")
    }
    println("There were ${viableGroups.size} viable groups.")
}
