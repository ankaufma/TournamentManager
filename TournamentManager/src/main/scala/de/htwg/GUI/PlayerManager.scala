package de.htwg.GUI

import scala.swing.Dialog
import java.awt.FlowLayout
import scala.swing._
import scala.swing.event.ButtonClicked
import scala.swing.event.ButtonClicked
import de.htwg.controller.Controller
import de.htwg.controller.NewPlayerState
import scala.collection.mutable.ListBuffer
import de.htwg.model.Player

class PlayerManager(controller: Controller) extends Frame {
	title = "Player Management"
	
	//listenTo(controller)
	reactions += {
	  case nps: NewPlayerState =>
	    if(!boxpanel.contents.isEmpty) boxpanel.contents.clear
	    nps.p.sortByGoals.map(x =>
	      	boxpanel.contents += new FlowPanel {
	      	  vGap = 0
	      	  val player = new Label("Name")
	      	  player.minimumSize = new Dimension(40,15)
	  		  player.preferredSize = new Dimension(40,15)
	  		  player.maximumSize = new Dimension(40,15)
	      	  contents += player
	      	  val pn = new Label(x.name)
	      	  pn.minimumSize = new Dimension(120,15)
	  		  pn.preferredSize = new Dimension(120,15)
	  		  pn.maximumSize = new Dimension(120,15)
	    	  contents += pn
	    	  val goals = new Label("Goals")
	      	  goals.minimumSize = new Dimension(40,15)
	  		  goals.preferredSize = new Dimension(40,15)
	  		  goals.maximumSize = new Dimension(40,15)
	      	  contents += goals
	      	  val pg = new Label(x.goals.toString)
	      	  pg.minimumSize = new Dimension(25,15)
	  		  pg.preferredSize = new Dimension(25,15)
	  		  pg.maximumSize = new Dimension(25,15)
	    	  contents += pg
	    	  contents += Button("+") {
	    	    controller.incrementGoals(x.index)
	    	  }
	    	  contents += Button("-") {
	    	    controller.decrementGoals(x.index)
	    	  }
	    	  val assists = new Label("Assists")
	      	  goals.minimumSize = new Dimension(40,15)
	  		  goals.preferredSize = new Dimension(40,15)
	  		  goals.maximumSize = new Dimension(40,15)
	      	  contents += assists
	      	  val pa = new Label(x.assists.toString)
	      	  pg.minimumSize = new Dimension(25,15)
	  		  pg.preferredSize = new Dimension(25,15)
	  		  pg.maximumSize = new Dimension(25,15)
	    	  contents += pa
	    	  contents += Button("+") {
	    	    controller.incrementAssists(x.index)
	    	  }
	    	  contents += Button("-") {
	    	    controller.decrementAssists(x.index)
	    	  }
	    	  val yellow = new Label("Yellow Cards")
	      	  goals.minimumSize = new Dimension(40,15)
	  		  goals.preferredSize = new Dimension(40,15)
	  		  goals.maximumSize = new Dimension(40,15)
	      	  contents += yellow
	      	  val py = new Label(x.yellowCards.toString)
	      	  pg.minimumSize = new Dimension(25,15)
	  		  pg.preferredSize = new Dimension(25,15)
	  		  pg.maximumSize = new Dimension(25,15)
	    	  contents += py
	    	  contents += Button("+") {
	    	    controller.incrementYellowCards(x.index)
	    	  }
	    	  contents += Button("-") {
	    	    controller.decrementYellowCards(x.index)
	    	  }
	    	  val red = new Label("Red Cards")
	      	  goals.minimumSize = new Dimension(40,15)
	  		  goals.preferredSize = new Dimension(40,15)
	  		  goals.maximumSize = new Dimension(40,15)
	      	  contents += red
	      	  val pr = new Label(x.redCards.toString)
	      	  pg.minimumSize = new Dimension(25,15)
	  		  pg.preferredSize = new Dimension(25,15)
	  		  pg.maximumSize = new Dimension(25,15)
	    	  contents += pr
	    	  contents += Button("+") {
	    	    controller.incrementRedCards(x.index)
	    	  }
	    	  contents += Button("-") {
	    	    controller.decrementRedCards(x.index)
	    	  }
	      	  val benchedcb = new CheckBox("Benched!")
	    	  contents += benchedcb
	    	}
		)
		boxpanel.revalidate
		repaint
		pack
	  case _ => 
	}
	
	val boxpanel = new BoxPanel(Orientation.Vertical)
	
	controller.init
	
	contents = new BoxPanel(Orientation.Vertical) {
	  contents += new FlowPanel {
	    vGap = 0
	    contents += new Label("Player")
	    val player = new TextField("", 20)
	    contents += player
	    contents += Button("Create Player") {
	    	controller.createPlayer(player.text)
	    	player.text = ""
	    }
	  }
	  contents += boxpanel
	}
	
	centerOnScreen
	open
}