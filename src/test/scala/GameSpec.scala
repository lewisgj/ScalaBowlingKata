
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}

class GameSpec extends FlatSpec with Matchers with BeforeAndAfter {

  var game: Game = _

  before {
    game = new Game
  }

  behavior of "Gutter game"
  it should "have score 0" in {

    rollMany(0, 20)

    game.score shouldEqual 0
  }


  behavior of "All ones"
  it should "have score 10" in {
    rollMany(1, 20)

    game.score shouldEqual 20
  }

  behavior of "A spare"
  it should "double the first roll of the next frame" in {
    rollFrame(5, 5)
    rollFrame(3, 0)

    game.score shouldEqual 16
  }

  behavior of "A strike"
  it should "include double next frame score" in {

    rollAStrike
    rollFrame(1,0)

    game.score shouldEqual 12
  }

  it should "add bonus points from next 2 strikes" in {
    rollAStrike
    rollAStrike
    rollAStrike

    game.score shouldEqual 60
  }

  behavior of "A perfect game"
  it should "have final score of 300" in {
    rollMany(10, 12)

    game.score shouldEqual 300
  }


  private def rollAStrike(): Unit = {
    game.roll(10)
  }

  private def rollFrame(first: Int, second: Int): Unit = {
    game.roll(first)
    game.roll(second)
  }

  private def rollMany(pins: Int, times: Int): Unit = {
    for (i <- 0 until times) {
      game.roll(pins)
    }
  }
}