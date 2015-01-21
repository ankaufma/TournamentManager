package de.htwg.model
import org.specs2.mutable._

/*
 * Created by Gian-Luca Piras 20.02.2015
 */

class PlayerSpec extends Specification {
  val player1 = Player(0, "Spieler1", 4, 3, 2, 1, false)
  val player2 = Player(1, "Spieler2", 1, 2, 3, 4, true)

  " Player 1" should {
    "have been created with name 'Spieler1'" in {
      player1.name must be_==("Spieler1")
    }
  }

  "Player 2" should {
    "have been created with name 'Spieler2'" in {
      player2.name must be_==("Spieler2")
    }

    "Player 1" should {
      "have the index '0'" in {
        player1.index must be_==(0)
      }
    }

    "Player 2" should {
      "have the index '1'" in {
        player2.index must be_==(1)
      }
    }
    
    "The Index" should {
      "be different from Player1 to Player2" in {
        player1.index must be_!=(player2.index)
      }
    }
    
    "Player 1" should {
      "have been scored more goals than Player 2" in {
        player1.goals must beGreaterThan(player2.goals)
      }
    }
    
    "Player 1" should{
      "have been more assists than Player 2" in {
        player1.assists must beGreaterThan(player2.assists)
      }
    }
    
    "Player 1" should{
      "have been less yellow cards than Player2" in{
        player1.yellowCards must beLessThan(player2.yellowCards)
      }
    }
    
    "Player 1" should{
      "have been less red cards than Player2" in {
        player1.redCards must beLessThan(player2.redCards)
      }
    }
    
    "Player 1" should{
      "play at the next game" in {
        player1.isBenched must be_==(false)
      }
    }
    
    "Player 2" should{
      "not play at the next game" in {
        player2.isBenched must be_==(true)
      }
    }
  }
}