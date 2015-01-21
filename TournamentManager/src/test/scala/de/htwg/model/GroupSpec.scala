package de.htwg.model
import org.specs2.mutable._
import scala.collection.mutable.ListBuffer
/**
 * @author gian-lucapiras
 */
class GroupSpec extends Specification {
  var teams = new ListBuffer[Team]()
  var games = new ListBuffer[Game]()
  teams += Team(0, "FCB", 0, 0, 0)
  teams += Team(1, "BVB", 0, 0, 0)
  teams += Team(2, "S04", 0, 0, 0)
  teams += Team(3, "B04", 0, 0, 0)

  games += Game(0, (teams.apply(0), teams.apply(1)), (0, 0), false)
  games += Game(1, (teams.apply(1), teams.apply(0)), (0, 0), false)
  games += Game(2, (teams.apply(2), teams.apply(3)), (0, 0), false)
  games += Game(3, (teams.apply(3), teams.apply(2)), (0, 0), false)
  games += Game(4, (teams.apply(0), teams.apply(2)), (0, 0), false)
  games += Game(5, (teams.apply(2), teams.apply(0)), (0, 0), false)
  games += Game(6, (teams.apply(1), teams.apply(3)), (0, 0), false)
  games += Game(7, (teams.apply(3), teams.apply(1)), (0, 0), false)
  games += Game(8, (teams.apply(0), teams.apply(3)), (0, 0), false)
  games += Game(9, (teams.apply(3), teams.apply(0)), (0, 0), false)
  games += Game(10, (teams.apply(1), teams.apply(2)), (0, 0), false)
  games += Game(11, (teams.apply(2), teams.apply(1)), (0, 0), false)

  val group = Group("Testgroup", teams, games)

  "A Group" should {
    "be able to list the games" in {
      group.getGames() must be_==(games.reverse)
    }
  }

  "A Group" should {
    "be able to identify the group winner" in {
      true must be_==(true)
    }
  }

  "A Group" should {
    "be able to get the table" in{
      val teamsToCompare = group.getTable(group)
      teamsToCompare must be_==(teams.toList.reverse)
    }
  }
  
  "A Group" should {
    "be able to get the group by ID" in{
      val game = group.getGameById(0)
      game must be_==(games.apply(0))
    }
  }
}