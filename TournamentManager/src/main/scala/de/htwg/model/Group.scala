package de.htwg.model

case class Group (val name: String, val teams: List[Team]) {
	var games = List[Game]()	
  	
	def calcMatches(): Unit = {
	  var k = 0
	  var l = 0
	  for(i <- 0 to teams.size-1; j <- 0 to teams.size-1 if(i!=j)) {
	   if ((i+1) > teams.size/2) return
	   games ::= new Game(l, (teams(k),teams(j)))
	   l+=1
	   if (k<teams.size-1) k += 1
	   else k = 0
	  }
	}
	
	def getGames() = games.reverse
	
	def getTable = sort(this.teams)
	
	def sort(teams: List[Team]): List[Team] = {
	  teams match {
	    case Nil => teams
	    case _ => sort(teams.tail.filter(_.points <= teams.head.points)) ::: teams.head :: sort(
	        teams.tail.filter(_.points > teams.head.points))
	  }
	}
}