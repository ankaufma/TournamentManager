package de.htwg.controller

import scala.collection.mutable.ListBuffer
import scala.swing.event.Event
import de.htwg.model.Team
import de.htwg.model.Group
import scala.swing._

case class NewState(g: Group) extends Event 
case class TeamsInit extends Event 
case class GroupsInit extends Event 

class Controller extends Publisher {
	class EasyTable(group: Group) {
		def getEasyTable = getTable(group)
	}
	
  	val teams = new ListBuffer[Team]()
	val groups = new ListBuffer[Group]() 
	def initTeams(team: String) = {
  	  teams += Team(teams.size-1,team,0,0,0)
  	  publish(new TeamsInit) 
  	}
	
	def initGroups(countOfGroups: Int, group: String) = {
	  val groupTeams: ListBuffer[Team] = teams.slice(teams.size/countOfGroups*(groups.size), teams.size/countOfGroups*(groups.size+1))
	  groups += Group(group, groupTeams, ListBuffer())
	  publish(new GroupsInit) 
	}
	
	def setGameResult(group: Int, index: Int, goals: Int, goalsAgainst: Int) = {
		val game = groups.flatMap(x => x.getGames)
		val gameIndex = (groups(0).getGames.size)*(group-1)+(index-1)
		groups(0).teams.foreach(x => println(x.points))
		groups(group-1).refractorGamePlan(game(gameIndex).setResult(goals,goalsAgainst))
		groups(group-1).setGameResult(index, (goals,goalsAgainst))
		groups(0).teams.foreach(x => println(x.points))
	    publish(new NewState(groups(group-1)))
	}
	
	def getTable(group: Group) = {
	  group.getTable(group)
	}

	implicit def getEasyTable(group: Group) = new EasyTable(group)
	
	def getGames(group: Group) = {
	  group.getGames
	}
}