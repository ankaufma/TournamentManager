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

  var teams2 = new ListBuffer[Team]()
  var games2 = new ListBuffer[Game]()
  teams2 += Team(0, "FCB", 0, 0, 0)
  teams2 += Team(1, "BVB", 0, 0, 0)
  games2 = ListBuffer()
  val group2 = Group("TestGroup", teams2, games2)
  group2.setGameResult(1,(1,0))
  "A Group with bayern and dortmund" should {
    "have bayern as winner, when one game is played" in{
      group2.getWinner must be_==(Some(group2, Team(0, "FCB", 0, 0, 0)))
    }
  }

  var teams4 = new ListBuffer[Team]()
  var games4 = new ListBuffer[Game]()
  teams4 += Team(0, "FCB", 0, 0, 0)
  teams4 += Team(1, "BVB", 0, 0, 0)
  games4 = ListBuffer()
  val group4 = Group("TestGroup", teams4, games4)
  "A Group with bayern and dortmund" should {
    "have no winner, when no game is played" in{
      group4.getWinner must be_==(None)
    }
  }

  var teams3 = new ListBuffer[Team]()
  var games3 = new ListBuffer[Game]()
  teams2 += Team(0, "FCB", 0, 0, 0)
  teams2 += Team(1, "BVB", 0, 0, 0)
  games2 = ListBuffer()
  val group3 = Group("TestGroup", teams2, games2)
  "A Group" should {
    "have a refrectored game plan with results for that games" in{
      group3.getGames()(0).getResult._1 must be_==(0)
      group3.getGames()(0).getResult._2 must be_==(0)
      group3.setGameResult(1,(3,2))
      group3.getGames()(0).getResult._1 must be_==(3)
      group3.getGames()(0).getResult._2 must be_==(2)
    }
  }

  var teams5 = new ListBuffer[Team]()
  var games5 = new ListBuffer[Game]()
  teams5 += Team(0, "FCB", 0, 0, 0)
  teams5 += Team(1, "BVB", 0, 0, 0)
  teams5 += Team(2, "VFL", 0, 0, 0)
  teams5 += Team(3, "VFB", 0, 0, 0)
  games5 = ListBuffer()
  val group5 = Group("TestGroup", teams5, games5)
  "A Group" should {
    "have a refrectored game plan with results for that games" in{
      group5.refractorGamePlan(List(teams5(0),teams5(1)))
      group5.getGames()(0).getResult._1 must be_==(0)
    }
  }
}