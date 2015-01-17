package de.htwg.GUI

import scala.swing.Dialog
import java.awt.FlowLayout
import scala.swing._
import scala.swing.event.ButtonClicked
import scala.swing.event.ButtonClicked
import de.htwg.controller.Controller
import de.htwg.actorCommunication
import scala.collection.mutable.ListBuffer
import de.htwg.model._

class GroupWinner(g: Group, t: Team) extends Frame {
	title = "WINNER!"
	
	contents = new BoxPanel(Orientation.Vertical) {
	  contents += new FlowPanel {
	    vGap = 0
	    contents += new Label("Winner of Group " + g.name + " is " + t.name)
	  }
	}
	
	centerOnScreen
	open
}