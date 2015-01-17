package de.htwg.TM

import de.htwg.controller._
import de.htwg.TUI.TUI
import de.htwg.GUI.GUI
import akka.actor.Actor
import akka.actor.Props
import akka.event.Logging
import akka.actor.ActorSystem

object TournamentManager extends App {
  val controller = new Controller()
  val gui = new GUI(controller)
  val tui = new TUI(controller)
	while(tui.routine(readLine)){}
}