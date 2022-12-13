import GameChoices.ROCK
import GameChoices.PAPER
import GameChoices.SCISSORS

fun main() {
    val inputData = readInput("Day02_input_sample")
    val rock =
        GameOption(
            choice = ROCK,
            choicePoint = 1,
            firstPlayerChoice = 'A',
            secondPlayerChoice = 'X',
            winOver = SCISSORS
        )
    val paper =
        GameOption(choice = PAPER, choicePoint = 2, firstPlayerChoice = 'B', secondPlayerChoice = 'Y', winOver = ROCK)
    val scissors = GameOption(
        choice = SCISSORS,
        choicePoint = 3,
        firstPlayerChoice = 'C',
        secondPlayerChoice = 'Z',
        winOver = PAPER
    )
    val gameRules = listOf(rock, paper, scissors)

    val pointsMap = mapOf("win" to 6, "lost" to 0, "draw" to 3)
    partOneGamePoints(inputData, gameRules, pointsMap)
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
        if (secondPlayer?.winOver == firstPlayer?.choice) {
            secondPlayer?.choicePoint?.plus(pointsMap["win"] ?: 6)
        } else if (firstPlayer?.winOver == secondPlayer?.choice) {
            secondPlayer?.choicePoint?.plus(pointsMap["lost"] ?: 0)
        } else {
            secondPlayer?.choicePoint?.plus(pointsMap["draw"] ?: 3)
        }
    }
    println("Part 1 -> Game Point Total: ${gamePoints.sumOf { it!! }}")
}

enum class GameChoices {
    ROCK,
    PAPER,
    SCISSORS
}

data class GameOption(
    val choice: GameChoices,
    val choicePoint: Int,
    val firstPlayerChoice: Char,
    val secondPlayerChoice: Char,
    val winOver: GameChoices
)