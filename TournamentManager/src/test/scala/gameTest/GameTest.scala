package gameTest

import de.htwg.model.{Team, Game}
import org.specs2.mutable._

/**
 * Created by ankaufma on 20.01.2015.
 */
class GameTest extends Specification {
  val bayern = Team(0,"FCB",0,0,0)
  val dortmund = Team(0,"BVB",0,0,0)
  val game = Game(1, (bayern, dortmund), (0, 0), false)

  "A new game" should {
    "have been createtd with name with Bayern vs. Dortmund should beend have created not played yet" in {
      game.index must be_==(1)
      game.getMatch._1.name must be_==("FCB")
      game.getMatch._2.name must be_==("BVB")
      game.getResult._1 must be_==(0)
      game.getResult._2 must be_==(0)
      game.isPlayed must be_==(false)
    }
  }

  "Calling get Match" should {
    "return a List of two teams and getResult a tuple of ints" in {
      game.getMatch must be_==((bayern, dortmund))
      game.getResult must be_==((0,0))
    }
  }

  val teamsAfterGameHome = game.setResult(1,0) match {
    case Some(teams) => teams
    case None => ???
  }
  "After setting a resuld" should {
    "with home team won should return a new list fo teams with 3 points for home" in {
      teamsAfterGameHome(0).goals must be_==(1)
      teamsAfterGameHome(1).goals must be_==(0)
      teamsAfterGameHome(0).points must be_==(3)
      teamsAfterGameHome(1).points must be_==(0)
      teamsAfterGameHome(0).goalsAgainst must be_==(0)
      teamsAfterGameHome(1).goalsAgainst must be_==(1)
    }
  }

  val teamsAfterGameRemis = game.setResult(1,1) match {
    case Some(teams) => teams
    case None => ???
  }
  "After setting a resuld" should {
    "with home team won should return a new list fo teams with 1 points both" in {
      teamsAfterGameRemis(0).goals must be_==(1)
      teamsAfterGameRemis(1).goals must be_==(1)
      teamsAfterGameRemis(0).points must be_==(1)
      teamsAfterGameRemis(1).points must be_==(1)
      teamsAfterGameRemis(0).goalsAgainst must be_==(1)
      teamsAfterGameRemis(1).goalsAgainst must be_==(1)
    }
  }

  val teamsAfterGameForeign = game.setResult(0,1) match {
    case Some(teams) => teams
    case None => ???
  }
  "After setting a resuld" should {
    "with home team won should return a new list fo teams with 3 points for foriegn" in {
      teamsAfterGameForeign(0).goals must be_==(1)
      teamsAfterGameForeign(1).goals must be_==(0)
      teamsAfterGameForeign(0).points must be_==(3)
      teamsAfterGameForeign(1).points must be_==(0)
      teamsAfterGameForeign(0).goalsAgainst must be_==(0)
      teamsAfterGameForeign(1).goalsAgainst must be_==(1)
    }
  }

  val gamePlayed = Game(1, (bayern, dortmund), (0, 0), true)
  "A Already played game" should {
    "return none on setResult" in {
      gamePlayed.setResult(0,0) must be_==(None)
    }
  }

  val gameNotPlayed = Game(1, (bayern, dortmund), (0, 0), false)
  "A Already played game" should {
    "return some on setResult" in {
      gameNotPlayed.setResult(0,0) must be_==(Some(List(bayern.copy(points=1), dortmund.copy(points=1))))
    }
  }

}
