package de.htwg.model

class Game(index: Int, game: (Team, Team)) {
  
 var result: (Int, Int) = (-1,-1)
 
 def setResult(r: (Int, Int)): List[Team] = {
   result = r
   if(r._1 > r._2) {
     List(game._1.copy(
       points = game._1.points + 3,
       goals = game._1.goals + r._1, 
       goalsAgainst = game._1.goalsAgainst + r._1),
    game._2.copy(
       goals = game._2.goals + r._2, 
       goalsAgainst = game._2.goals + r._2))
   } else 
   if(r._1 < r._2) {
     List(game._2.copy(
       points = game._2.points + 3, 
       goals = game._2.goals + r._2, 
       goalsAgainst = game._2.goals + r._2),
     game._1.copy(
       goals = game._1.goals + r._1, 
       goalsAgainst = game._1.goalsAgainst + r._1))
   } else
   if(r._1 == r._2) {
     List(game._1.copy(
       points = game._1.points + 1,
       goals = game._1.goals + r._1, 
       goalsAgainst = game._1.goalsAgainst + r._1),
     game._2.copy(
       points = game._2.points + 1, 
       goals = game._2.goals + r._2, 
       goalsAgainst = game._2.goals + r._2))
   } else {
     List(Team(0,"",0,0,0))
   }
 }
 
 def getResult = result
 
 def getMatch = game
 
}