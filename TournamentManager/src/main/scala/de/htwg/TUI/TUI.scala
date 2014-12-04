package de.htwg.TUI

import de.htwg.model.Team
import scala.collection.mutable.ListBuffer
import de.htwg.model.Group
import de.htwg.controller.Controller

object TUI extends Application {
	
	//Initialization of Basic Data...
	println("Please type in the number of teams: ")
	val noTeams: Int = readInt
	for(i <- 1 to noTeams) {
	  println("Please type in the Team Name: ")
	  val teamName = readLine
	  Controller.initTeams(teamName)
	}
	
	println("Please type in the number of groups: ")
	val countOfGroups: Int = readInt
	for(i <- 1 to countOfGroups) {
	  println("Please type in the Group Name: ")
	  val groupName = readLine
	  Controller.initGroups(countOfGroups, groupName)
	}

	routine
		
	def routine() = {
	  printMenu()
	  while(true) {
	    val input = readLine 
	    input.toList match {
	      case 't' :: rest => Controller.groups.foreach(printTable(_))
	      case 'T' :: rest => Controller.groups.foreach(printTable(_))
	      case 'm' :: 'a' :: rest => Controller.groups.foreach(printMatches(_))
	      case 'M' :: 'a' :: rest => Controller.groups.foreach(printMatches(_))
	      case 'm' :: 'e' :: rest => printMenu()
	      case 'M' :: 'e' :: rest => printMenu()
	      case 'q' :: rest => System.exit(0)
	      case 'Q' :: rest => System.exit(0) 
	      case _ => {
	    	 try { 
	    	   input.replaceAll(":", " ").replaceAll("\\.", "").split(" ").toList.map(_.toInt) match {
	    	 	case group :: index :: goals :: goalsAgainst :: Nil => 
	    	 		printTable(Controller.setGameResult(group, index, goals, goalsAgainst))
	    	 	case _ => println("omG an Error occured")
	    	   }
	    	 } catch {
	    	   case nfe: NumberFormatException => 
	    	   	println("*****************************")
	    	   	println("Please start with a number...")
	    	   	println("*****************************")
	    	   case iob: IndexOutOfBoundsException => 
	    	    println("*****************************")
	    	   	println("That index doesn't exist...")
	    	   	println("*****************************")
	    	 }
	      }
	    }
	  }
	}
	
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
	
	def printTable(group: Group) = {
	  val teams = Controller.getTable(group)
	  println("====================================")
	  println("TABLE OF GROUP " + group.name )
	  println("Team name | Points | Goals | Goals against")
	  teams.reverse.foreach(y => 
	          println(y.name + " | " + y.points + " | " + y.goals + " | " + y.goalsAgainst))
	  println("====================================")
	}
	
	def printMatches(group: Group) {
		println("====================================")
	  	println("MATCHES OF GROUP " + group.name);
	  	println("Matchindex. Home team : Foreign Team")
	  	Controller.getGames(group).foreach(y => 
	  	println(y.index + ". " + y.getMatch._1.name + "-" + y.getMatch._2.name + " " + y.r._1 + ":" + y.r._2))
		println("====================================")
	}

}