class Game {

  var rolls: Array[Int] = Array.fill[Int](21)(0)

  var rollCount = 0

  def roll(pins: Int): Unit = {
    rolls(rollCount) = pins
    rollCount = rollCount + 1
  }

  def score(): Int = {

    var score = 0

    var frameIndex = 0

    for (frame <- 0 until 10) {
      if (isStrike(frameIndex)) {
        score += 10 + strikeBonus(frameIndex)
        frameIndex += 1
      }
      else if (isSpare(frameIndex)) {
        score += 10 + spareBonus(frameIndex)
        frameIndex += 2
      }
      else {
        score += sumOfBallsInFrame(frameIndex)
        frameIndex += 2
      }
    }

    score
  }

  private def isStrike(frameIndex: Int) = rolls(frameIndex) == 10

  private def sumOfBallsInFrame(frameIndex: Int) = rolls(frameIndex) + rolls(frameIndex + 1)

  private def spareBonus(frameIndex: Int) = rolls(frameIndex + 2)

  private def strikeBonus(frameIndex: Int) = rolls(frameIndex + 1) + rolls(frameIndex + 2)

  private def isSpare(frameIndex: Int) = rolls(frameIndex) + rolls(frameIndex + 1) == 10

}
