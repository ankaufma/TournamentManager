package de.htwg.TM

import de.htwg.controller.Controller
import de.htwg.TUI.TUI
import de.htwg.GUI.GUI

object TournamentManager extends App {
	val controller = new Controller()
    val tui = new TUI(controller)
    val gui = new GUI(controller)

	while(tui.routine(readLine)){}
}