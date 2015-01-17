package de.htwg.TUI

import de.htwg.model.Team
import akka.actor.Actor
import akka.actor.Props
import akka.event.Logging
import scala.swing._
import scala.collection.mutable.ListBuffer
import de.htwg.model.Group
import de.htwg.controller._

class TUI (controller: Controller) extends Reactor {
	listenTo(controller)
  
	reactions += {
	  case a: NewState => 
	    printMatches(a.g)
	    printTable(a.g)
	  case _ => 
	}
	
	def init() {
	println("Please type in the number of teams: ")
	val noTeams: Int = readInt
	for(i <- 1 to noTeams) {
	  println("Please type in the Team Name: ")
	  val teamName = readLine
	  controller.initTeams(teamName)
	}
	
	println("Please type in the number of groups: ")
	val countOfGroups: Int = readInt
	for(i <- 1 to countOfGroups) {
	  println("Please type in the Group Name: ")
	  val groupName = readLine
	  controller.initGroups(countOfGroups, groupName)
	}}
		
	def routine(input: String) = {
	  printMenu()
	  var continue = true
	    input.toList match {
	      case 't' :: rest => controller.groups.foreach(printTable(_))
	      case 'T' :: rest => controller.groups.foreach(printTable(_))
	      case 'm' :: 'a' :: rest => controller.groups.foreach(printMatches(_))
	      case 'M' :: 'a' :: rest => controller.groups.foreach(printMatches(_))
	      case 'm' :: 'e' :: rest => printMenu()
	      case 'M' :: 'e' :: rest => printMenu()
	      case 'q' :: rest => continue = false
	      case 'Q' :: rest => continue = false
	      case _ => {
	    	 try { 
	    	   input.replaceAll(":", " ").replaceAll("\\.", "").split(" ").toList.map(_.toInt) match {
	    	 	case group :: index :: goals :: goalsAgainst :: Nil => 
	    	 		controller.setGameResult(group, index, goals, goalsAgainst)
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
	  continue
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
	  val teams = controller.getTable(group)
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
	  	controller.getGames(group).foreach(y => 
	  	println(y.index + ". " + y.getMatch._1.name + "-" + y.getMatch._2.name + " " + y.r._1 + ":" + y.r._2))
		println("====================================")
	}

}