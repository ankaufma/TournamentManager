package de.htwg.model

import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks._

case class Group (name: String, teams: ListBuffer[Team], games: ListBuffer[Game]) {		
	// Calculates the Matches of the group
	// Gameplan has to be immutable but updatable (ListBuffer)
	var k = 0
	var l = (teams.size-1)*(teams.size/2)
	breakable { 
	   for(i <- 0 until teams.size; j <- 0 to teams.size-1 if(i!=j)) {
		   if ((i+1) > teams.size/2) break 
		   else {
			   games += new Game(l, (teams(k),teams(j)), (0,0))
			   l-=1
			   if (k<teams.size-1) k += 1
			   else k = 0
		   }
		}
	}
	
	def getGames() = games.reverse
	
	def getTable (group: Group): List[Team] = sort(group.teams)
	def getTable = sort(this.teams)
	
	def sort(teams: ListBuffer[Team]): List[Team] = {
	  teams.toList.
	  sortWith((x,y) => x.goals						>	y.goals).
	  sortWith((x,y) => (x.goals-x.goalsAgainst) 	> 	(y.goals-y.goalsAgainst)).
	  sortWith((x,y) => x.points 					> 	y.points)
	  .reverse
	}
	
	def printMatches = {
		println("====================================")
	  	println("MATCHES OF GROUP " + this.name);
	  	println("Matchindex. Home team : Foreign Team")
	  	this.getGames.foreach(y => 
	  	  	println(y.index + ". " + y.getMatch._1.name + "-" + y.getMatch._2.name + " " + y.r._1 + ":" + y.r._2))
		println("====================================")
	}
	
	def refractorGamePlan(teams: List[Team]) = {
	  // Refractor game plan with copie of Teams to be able to sort points and goals
	  for(g <- this.games; t <- teams) {
	    if(g.game._1.index == t.index) {
	      this.games.update(this.games.size-g.index, g.copy(game = (t, g.game._2)))
	    }
	    if(g.game._2.index == t.index) {
	      this.games.update(this.games.size-g.index, g.copy(game = (g.game._1, t)))
	    }
	  }
	  // Refractor Teams in GroupList to recalculate the Table with new Copies of immutable Teams
	  for(x <- this.teams; y <- teams; if(x.index == y.index)) {
	    this.teams.update(this.teams.indexOf(x), y)
	  }
	}
	
	def setGameResult(index: Int, result: (Int, Int)) {
	  this.games.update(this.games.size-index, this.games(this.games.size-index).copy(r = (result._1, result._2)))
	}
}