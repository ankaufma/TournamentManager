package de.htwg.model

import scala.collection.mutable.ListBuffer

class PlayerManager (players: ListBuffer[Player]) {
  
  def createPlayer(name: String) {
    players += Player(players.size,name,0,0,0,0,false)
  }
  
  def sortByGoals = {
    players.sortWith(_.assists > _.assists).sortWith(_.goals > _.goals)
  }
  
  def incrementGoals(index: Int) {
    players.update(index, players(index).copy(goals = players(index).goals + 1))
  }
  
  def decrementGoals(index: Int) {
    players.update(index, players(index).copy(goals = players(index).goals - 1))    
  }
  
  def incrementAssists(index: Int) {
    players.update(index, players(index).copy(assists = players(index).assists + 1))
  }
  
  def decrementAssists(index: Int) {
    players.update(index, players(index).copy(assists = players(index).assists - 1))    
  }
    def incrementYellowCards(index: Int) {
    players.update(index, players(index).copy(yellowCards = players(index).yellowCards + 1))
  }
  
  def decrementYellowCards(index: Int) {
    players.update(index, players(index).copy(yellowCards = players(index).yellowCards - 1))    
  }
  def incrementRedCards(index: Int) {
    players.update(index, players(index).copy(redCards = players(index).redCards + 1))
  }
  
  def decrementRedCards(index: Int) {
    players.update(index, players(index).copy(redCards = players(index).redCards - 1))    
  }
  
  def setBenched(index: Int) {
    if(players(index).isBenched == false) {
    	players.update(index, players(index).copy(isBenched = true))  
    } else {
    	players.update(index, players(index).copy(isBenched = false))
    }
  }
}