package de.htwg.TM

import de.htwg.controller._
import de.htwg.TUI._
import de.htwg.GUI.GUI
import akka.actor._
import akka.actor.ActorSystem
import de.htwg.actorCommunication._
import de.htwg.controller._
import com.escalatesoft.subcut.inject._
import NewBindingModule._


object TournamentManager extends App {
    implicit val bindingModule: BindingModule = SubCutConfig
    val controller = new Inject().controller
    val tui = new TUI(controller)
    val gui = new GUI(controller)
    while(tui.routine(readLine)) {}

//    val system = ActorSystem.create("MySystem")
//    val actorcontroller = system.actorOf(Props[ActorController], "Controller")
//    val tuiactor = system.actorOf(Props[TUIActor], "TUI")
//    tuiactor ! "Start"
}