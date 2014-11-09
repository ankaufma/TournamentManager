package de.htwg.TUI

import de.htwg.model.Team
import scala.collection.mutable.ListBuffer
import de.htwg.model.Group

object TUI extends Application {
	val teams = new ListBuffer[Team]()
	val groups = new ListBuffer[Group]()
	
	//Initialization of Basic Data...
	println("Please type in the number of teams: ")
	val noTeams: Int = readInt
	for(i <- 1 to noTeams) {
	  println("Please type in the Team Name: ")
	  val teamName = readLine
	  teams += Team(i,teamName,0,0,0)
	}
	val teamList = teams.toList
	
	println("Please type in the number of groups: ")
	val noGroups: Int = readInt
	var from = 0
	var until = teamList.size/noGroups
	for(i <- 1 to noGroups) {
	  println("Please type in the Group Name: ")
	  val groupName = readLine
	  val teamsGroup = teams.slice(from, until)
	  groups += Group(groupName, teamsGroup, ListBuffer())
	  from += teamList.size/noGroups
	  until += teamList.size/noGroups
	}
	val groupList = groups.toList

	routine

	def printMenu() = {
		println("========================================================")
	    println("Type in 'table' to show the table")
	    println("Or type in 'matches' to show the matches")
	    println("Or type in 'quit' to stop the program")
	    println("Or type in 'menu' to show the Menu Information again")
	    println("Type in Group-Index, Match-Index and result")
	    println("For example '1 1 3:2' for Group A, Game 1, result is 3:2")
	    println("========================================================")
	}
		
	def routine() = {
	  printMenu()
	  while(true) {
	    val input = readLine 
	    input.toList match {
	      case 't' :: rest => groupList.foreach(_.getTable)
	      case 'T' :: rest => groupList.foreach(_.getTable)
	      case 'm' :: 'a' :: rest => groupList.foreach(_.printMatches)
	      case 'M' :: 'a' :: rest => groupList.foreach(_.printMatches)
	      case 'm' :: 'e' :: rest => printMenu()
	      case 'M' :: 'e' :: rest => printMenu()
	      case 'q' :: rest => System.exit(0)
	      case 'Q' :: rest => System.exit(0) 
	      case _ => {
	    	 try { 
	    	   input.toList.filter(x => x != ' ' && x != '.' && x != ':' ).map(_.toString.toInt) match {
	    	 	case group :: index :: goals :: goalsAgainst :: Nil => 
	    	 		val game = groupList.flatMap(x => x.getGames)
	    	 		val gameIndex = (groupList(0).getGames.size)*(group-1)+(index-1)
	    	 		groupList(group-1).refractorGamePlan(game(gameIndex).setResult(goals,goalsAgainst))
	    	 		groupList(group-1).setGameResult(index, (goals,goalsAgainst))
	    	 		groupList(group-1).getTable
	    	 	case _ => println("omG an Error occured")
	    	   }
	    	 } catch {
	    	   case nfe: NumberFormatException => 
	    	   	println("*****************************")
	    	   	println("Please start with a number...")
	    	   	println("*****************************")
	    	   //case iob: IndexOutOfBoundsException => 
	    	    println("*****************************")
	    	   	println("That index doesn't exist...")
	    	   	println("*****************************")
	    	 }
	      }
	    }
	  }
	}

}