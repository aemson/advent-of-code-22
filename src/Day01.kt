fun main() {
    val inputData = readInput("Day01_input")
    val elfCalories =
        inputData
            .split { it.isBlank() }
            .map { strings -> (strings.sumOf { it.toInt() }) }
    println("Part One (Most calories)-> ${elfCalories.maxOf { it }}")
    println("Part Two (Last 3 calories)-> ${elfCalories.sorted().takeLast(3).sum()}")
}