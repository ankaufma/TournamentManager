package controllers

import scala.collection.mutable.ListBuffer
import models._

object Controller {
  class EasyTable(group: Group) {
    def getEasyTable = getTable(group)
  }

  val groupWinners = new ListBuffer[Team]()
  val players = new PlayerManager(ListBuffer())
  val teams = new ListBuffer[Team]()
  val groups = new ListBuffer[Group]()
  def initTeams(team: String) = {
    teams += Team(teams.size-1,team,0,0,0)
    //publish(TeamsInit)
  }

  def createPlayer(name: String) = {
    players.createPlayer(name)
    //publish(NewPlayerState(players))
  }
  def incrementGoals(index: Int) = {
    players.incrementGoals(index)
    //publish(NewPlayerState(players))
  }
  def decrementGoals(index: Int) = {
    players.decrementGoals(index)
    //publish(NewPlayerState(players))
  }
  def incrementAssists(index: Int) = {
    players.incrementAssists(index)
    //publish(NewPlayerState(players))
  }
  def decrementAssists(index: Int) = {
    players.decrementAssists(index)
    //publish(NewPlayerState(players))
  }
  def incrementYellowCards(index: Int) = {
    players.incrementYellowCards(index)
    //publish(NewPlayerState(players))
  }
  def decrementYellowCards(index: Int) = {
    players.decrementYellowCards(index)
    //publish(NewPlayerState(players))
  }
  def incrementRedCards(index: Int) = {
    players.incrementRedCards(index)
    //publish(NewPlayerState(players))
  }
  def decrementRedCards(index: Int) = {
    players.decrementRedCards(index)
    //publish(NewPlayerState(players))
  }
  def setBenched(index: Int) = {
    players.setBenched(index)
    //publish(NewPlayerState(players))
  }
  def init = {
    //publish(NewPlayerState(players))
  }

  def initGroups(countOfGroups: Int, group: String) = {
    val groupTeams: ListBuffer[Team] = teams.slice(teams.size/countOfGroups*(groups.size), teams.size/countOfGroups*(groups.size+1))
    groups += Group(group, groupTeams, ListBuffer())
    //publish(GroupsInit)
  }

  def setGameResult(group: Int, index: Int, goals: Int, goalsAgainst: Int) = {
    val game = groups.flatMap(x => x.getGames)
    val gameIndex = (groups(0).getGames.size)*(group-1)+(index-1)
    game(gameIndex).setResult(goals,goalsAgainst) match {
      case Some(teams) => groups(group-1).refractorGamePlan(teams)
      case None => println("Game was already played...")
    }
    groups(group-1).setGameResult(index, (goals,goalsAgainst))
    groups(group-1).getWinner match {
      case None => println("No Group Winner yet")
      case Some((a,b)) => groupWinners += b; //publish(GroupWinnerFound(a,b))
    }
    //publish(NewState(groups(group-1)))
  }

  def getTable(group: Group) = {
    group.getTable(group)
  }

  implicit def getEasyTable(group: Group) = new EasyTable(group)

  def getGames(group: Group) = {
    group.getGames
  }

  def playBackSeason() {
    groups.foreach { group =>
      for(i <- 0 until group.games.size) {
        group.games.update(i, group.games(i).copy(isPlayed = false))
      }
    }
  }
}