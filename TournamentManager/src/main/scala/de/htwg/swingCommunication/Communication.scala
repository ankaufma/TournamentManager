package de.htwg.swingCommunication
import de.htwg.model._
import scala.swing.event._

//Controller
case class NewState(g: Group) extends Event
case class NewPlayerState(p: PlayerManager) extends Event
case object TeamsInit extends Event 
case object GroupsInit extends Event
case object Quit extends Event
case class GroupWinnerFound(g:Group,t:Team) extends Event 
case class AllGroups(groups: List[Group]) extends Event
