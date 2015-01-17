package de.htwg.TM

import de.htwg.controller._
import de.htwg.TUI._
import de.htwg.GUI.GUI
import akka.actor._
import akka.actor.ActorSystem
import de.htwg.actorCommunication._

object TournamentManager extends App {
  val uicontroller = new Controller()
//  val system = ActorSystem.create("MySystem");
//  val actorcontroller = system.actorOf(Props[ActorController], "Controller")
//  val tuiactor = system.actorOf(Props[TUIActor], "TUI")
//  tuiactor ! "Start"
  val tui = new TUI(uicontroller)
  val gui = new GUI(uicontroller)
  while(tui.routine(readLine)) {}
}