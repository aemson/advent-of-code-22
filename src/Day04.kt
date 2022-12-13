fun main() {
    val inputData = readInput("Day04_input")
    val campOrder: List<Pair<List<Int>, List<Int>>> = inputData.map { input ->
        val sectionsMap = input.split(",").map {
            val split = it.split("-")
            val start = split[0].toInt()
            val end = split[1].toInt()
            var numbs = mutableListOf<Int>()
            for (i in start..end) {
                numbs.add(i)
            }
            numbs.toList()
        }
        val firstList = sectionsMap[0]
        val secondList = sectionsMap[1]
        firstList to secondList
    }
    println("Camp Cleanup (Part 1) -> ${partOneSolution(campOrder)}")
    println("Camp Cleanup (Part 2) -> ${partTwoSolution(campOrder)}")
}

fun partOneSolution(campOrder: List<Pair<List<Int>, List<Int>>>) =
    campOrder.map { (firstList, secondList) ->
        if (firstList.containsAll(secondList) || secondList.containsAll(firstList))
            1
        else
            0
    }.sum()

fun partTwoSolution(campOrder: List<Pair<List<Int>, List<Int>>>) =
    campOrder.map { (firstList, secondList) ->
        if (firstList.intersect(secondList.toSet()).isNotEmpty() || secondList.intersect(firstList.toSet())
                .isNotEmpty()
        )
            1
        else
            0
    }.sum()