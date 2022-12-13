import GameChoices.ROCK
import GameChoices.PAPER
import GameChoices.SCISSORS

fun main() {
    val inputData = readInput("Day02_input")
    val rock =
        GameOption(
            choice = ROCK,
            choicePoint = 1,
            firstPlayerChoice = 'A',
            secondPlayerChoice = 'X',
            winOver = SCISSORS,
            loseOver = PAPER
        )
    val paper =
        GameOption(
            choice = PAPER,
            choicePoint = 2,
            firstPlayerChoice = 'B',
            secondPlayerChoice = 'Y',
            winOver = ROCK,
            loseOver = SCISSORS
        )
    val scissors = GameOption(
        choice = SCISSORS,
        choicePoint = 3,
        firstPlayerChoice = 'C',
        secondPlayerChoice = 'Z',
        winOver = PAPER,
        loseOver = ROCK
    )
    val gameRules = listOf(rock, paper, scissors)

    val pointsMap = mapOf("win" to 6, "lost" to 0, "draw" to 3)
    partOneGamePoints(inputData, gameRules, pointsMap)
    partTwoGamePoints(inputData, gameRules, pointsMap)
}

private fun partOneGamePoints(
    inputData: List<String>,
    gameRules: List<GameOption>,
    pointsMap: Map<String, Int>
) {
    val gamePoints = inputData.map { game ->
        val playerChoices = game.split(" ")
        val firstPlayerChoice = playerChoices[0].single()
        val secondPlayerChoice = playerChoices[1].single()

        val firstPlayer = gameRules.find { it.firstPlayerChoice == firstPlayerChoice }
        val secondPlayer = gameRules.find { it.secondPlayerChoice == secondPlayerChoice }
        calculatePoints(secondPlayer, firstPlayer, pointsMap)
    }
    println("Part 1 -> Game Point Total: ${gamePoints.sumOf { it!! }}")
}

/**
 * X means you need to lose, Y means you need to end the round in a draw, and Z means you need to win
 * */
fun partTwoGamePoints(
    inputData: List<String>,
    gameRules: List<GameOption>,
    pointsMap: Map<String, Int>
) {
    val newRule = mapOf('X' to "lost", 'Y' to "draw", 'Z' to "win")
    val gamePoints = inputData.map { game ->
        val playerChoices = game.split(" ")
        val firstPlayerChoice = playerChoices[0].single()
        val secondPlayerChoice = playerChoices[1].single()
        val firstPlayer = gameRules.find { it.firstPlayerChoice == firstPlayerChoice }
        val secondPlayer = when (secondPlayerChoice) {
            'X' -> gameRules.first { it.choice == firstPlayer?.winOver }
            'Y' -> gameRules.first { it.choice == firstPlayer?.choice }
            else -> gameRules.first { it.choice == firstPlayer?.loseOver }
        }
        calculatePoints(secondPlayer, firstPlayer, pointsMap)
    }
    println("Part 2 -> Game Point Total: ${gamePoints.sumOf { it!! }}")
}

private fun calculatePoints(
    secondPlayer: GameOption?,
    firstPlayer: GameOption?,
    pointsMap: Map<String, Int>
) = if (secondPlayer?.winOver == firstPlayer?.choice) {
    secondPlayer?.choicePoint?.plus(pointsMap["win"] ?: 6)
} else if (firstPlayer?.winOver == secondPlayer?.choice) {
    secondPlayer?.choicePoint?.plus(pointsMap["lost"] ?: 0)
} else {
    secondPlayer?.choicePoint?.plus(pointsMap["draw"] ?: 3)
}


enum class GameChoices {
    ROCK,
    PAPER,
    SCISSORS
}

data class GameOption(
    var choice: GameChoices,
    val choicePoint: Int,
    val firstPlayerChoice: Char,
    val secondPlayerChoice: Char,
    var winOver: GameChoices,
    var loseOver: GameChoices,
)