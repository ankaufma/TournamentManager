package de.htwg.TM

import de.htwg.controller._
import de.htwg.TUI._
import de.htwg.GUI.GUI
import akka.actor._
import akka.actor.ActorSystem
import de.htwg.actorCommunication._
import de.htwg.controller._
import com.escalatesoft.subcut.inject._

object TournamentManager extends App {
    override def main(args: Array[String]) {
    val controller = new RealController
//  val system = ActorSystem.create("MySystem");
//  val actorcontroller = system.actorOf(Props[ActorController], "Controller")
//  val tuiactor = system.actorOf(Props[TUIActor], "TUI")
//  tuiactor ! "Start"
    val tui = new TUI(controller)
    val gui = new GUI(controller)
    while(tui.routine(readLine)) {}
    }
}