package de.htwg.model
import org.specs2.mutable._
import scala.collection.mutable.ListBuffer

/**
 * @author gian-lucapiras
 */
class PlayerManagerSpec extends Specification {
  
  var players = new ListBuffer[Player]()
  players += Player(0, "Spieler1", 1, 0, 3, 4, true)
  players += Player(1, "Spieler2", 1, 1, 0, 0, false)
  players += Player(2, "Spieler3", 1, 2, 1, 1, true)
  players += Player(3, "Spieler4", 1, 3, 3, 4, false)
  players += Player(4, "Spieler5", 1, 4, 3, 4, true)
  players += Player(5, "Spieler6", 1, 5, 1, 4, false)
  players += Player(6, "Spieler7", 1, 6, 3, 1, true)
  players += Player(7, "Spieler8", 1, 7, 3, 4, false)
  players += Player(8, "Spieler9", 1, 8, 3, 4, true)
  players += Player(9, "Spieler10", 1, 9, 3, 4, false)
  players += Player(10, "Spieler11", 1, 10, 3, 4, true)
  val list = players.toList

  "A Player Manager" should {
    "be able to create a new Player" in {
      val pm = new PlayerManager(players)
      pm.createPlayer("Spieler12")
      players.apply(11).name must be_==("Spieler12")
    }
  }
  "A Player Manager" should {
    "be able to sort the Players by goals" in {
      val pm2 = new PlayerManager(list.to[ListBuffer])
      var players2 = new ListBuffer[Player]()
      var playersSorted = pm2.sortByGoals
      players2 += Player(10, "Spieler11", 1, 10, 3, 4, true)
      players2 += Player(9, "Spieler10", 1, 9, 3, 4, false)
      players2 += Player(8, "Spieler9", 1, 8, 3, 4, true)
      players2 += Player(7, "Spieler8", 1, 7, 3, 4, false)
      players2 += Player(6, "Spieler7", 1, 6, 3, 1, true)
      players2 += Player(5, "Spieler6", 1, 5, 1, 4, false)
      players2 += Player(4, "Spieler5", 1, 4, 3, 4, true)
      players2 += Player(3, "Spieler4", 1, 3, 3, 4, false)
      players2 += Player(2, "Spieler3", 1, 2, 1, 1, true)
      players2 += Player(1, "Spieler2", 1, 1, 0, 0, false)
      players2 += Player(0, "Spieler1", 1, 0, 3, 4, true)
      playersSorted must be_==(players2)
    }
  }

  "A Player Manager" should {
    "be able to increment a Players goals" in {
      var players2 = list.to[ListBuffer]
      val pm = new PlayerManager(players2)
      pm.incrementGoals(0)
      players2.apply(0).goals must be_==(2)
    }
  }

  "A Player Manager" should {
    "be able to decrement a Players goals" in {
      var players2 = list.to[ListBuffer]
      val pm = new PlayerManager(players2)
      pm.decrementGoals(0)
      players2.apply(0).goals must be_==(0)
    }
  }

  "A Player Manager" should {
    "be able to increment a Players assists" in {
      var players2 = list.to[ListBuffer]
      val pm = new PlayerManager(players2)
      pm.incrementAssists(0)
      players2.apply(0).assists must be_==(1)
    }
  }

  "A Player Manager" should {
    "be able to decrement a Players assists" in {
      var players2 = list.to[ListBuffer]
      val pm = new PlayerManager(players2)
      pm.decrementAssists(5)
      players2.apply(5).assists must be_==(4)
    }
  }

  "A Player Manager" should {
    "be able to increment a Players yellow cards" in {
      var players2 = list.to[ListBuffer]
      val pm = new PlayerManager(players2)
      pm.incrementYellowCards(0)
      players2.apply(0).yellowCards must be_==(4)
    }
  }

  "A Player Manager" should {
    "be able to decrement a Players yellow cards" in {
      var players2 = list.to[ListBuffer]
      val pm = new PlayerManager(players2)
      pm.decrementYellowCards(0)
      players2.apply(0).yellowCards must be_==(2)
    }
  }
  
  "A Player Manager" should {
    "be able to increment a Players red cards" in {
      var players2 = list.to[ListBuffer]
      val pm = new PlayerManager(players2)
      pm.incrementRedCards(0)
      players2.apply(0).redCards must be_==(5)
    }
  }
  
  "A Player Manager" should {
    "be able to decrement a Players red cards" in {
      var players2 = list.to[ListBuffer]
      val pm = new PlayerManager(players2)
      pm.decrementRedCards(0)
      players2.apply(0).redCards must be_==(3)
    }
  }
  
  "A Player Manager" should {
    "be able to set a Player from unbenched to benched" in {
      var players2 = list.to[ListBuffer]
      val pm = new PlayerManager(players2)
      pm.setBenched(1)
      players2.apply(1).isBenched must be_==(true)
    }
  }
  
  "A Player Manager" should {
    "be able to set a Player from benched to unbenched" in {
      var players2 = list.to[ListBuffer]
      val pm = new PlayerManager(players2)
      pm.setBenched(0)
      players2.apply(0).isBenched must be_==(false)
    }
  }
}