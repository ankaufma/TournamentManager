package de.htwg.actorCommunication

import akka.actor._
import de.htwg.model._

//TUI
sealed trait UINews
case class SetController(controller: ActorRef) extends UINews
case class InitTeams(team: String) extends UINews
case class InitGroups(countOfGroups: Int, groupName: String) extends UINews
case class SetGameResult(group: Int, index: Int, goals: Int, goalsAgainst: Int) extends UINews
case object GetAllGroups extends UINews

//Controller
sealed trait ControllerNews
case class NewState(g: Group) extends ControllerNews
case class NewPlayerState(p: PlayerManager) extends ControllerNews
case object TeamsInit extends ControllerNews 
case object GroupsInit extends ControllerNews 
case class GroupWinnerFound(g:Group,t:Team) extends ControllerNews 
case class AllGroups(groups: List[Group]) extends ControllerNews