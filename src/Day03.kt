fun main() {
    val inputData = readInput("Day03_input")
    val commonItems = inputData.map { item ->
        val numberOfItems = item.toCharArray().size
        val firstSet = item.substring(0, numberOfItems / 2).toCharArray().toList()
        val secondSet = item.substring(numberOfItems / 2, numberOfItems).toCharArray().toList()
        firstSet.intersect(secondSet.toSet()).first()

    }
    println("Rucksack Reorganization (Part 1) -> ${commonItems.map { it.priority() }.sumOf { it }}")
}

private fun Char.priority(): Int =
    when (this) {
        in 'a'..'z' -> (this - 'a') + 1
        in 'A'..'Z' -> (this - 'A') + 27
        else -> throw IllegalArgumentException("Letter not in range: $this")
    }