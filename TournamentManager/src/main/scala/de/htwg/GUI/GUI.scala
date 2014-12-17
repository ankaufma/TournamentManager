package de.htwg.GUI

import scala.swing._
import scala.swing.event.ButtonClicked
import de.htwg.controller._	
import java.awt.GridLayout

class GUI(controller: Controller) extends Frame {
  import controller.getEasyTable
  
  listenTo(controller)
  reactions += {
	case a: NewState => updateTables
	case _ => 
  }
  
  val initTnG = new InitTnGDialog().init.getOrElse(throw new IllegalStateException("WTF"))
  val initGroups = new InitGroupsDialog(initTnG.cog).init.getOrElse(throw new IllegalStateException("WTF"))
  val initTeams = new InitTeamsDialog(initTnG.cot).init.getOrElse(throw new IllegalStateException("WTF"))
  
  initTeams.teams.foreach(controller.initTeams(_))
  initGroups.groups.reverse.foreach(controller.initGroups(initTnG.cog, _))
  val tables = List.range(0,initTnG.cog).map(x => new Table(controller.groups(0).teams.size+1, 4))
  updateTables
  
  val boxPanels = for(i <- List.range(0,initTnG.cog)) yield
  	new BoxPanel(Orientation.Vertical) {
	  getGroupComponents(i).map(x => 
	    contents += x
	  )
	}
  
  val cols = initTnG.cog/2.toInt+1
  val myPanel = new GridPanel(2,cols) {
	for(i <- 0 until initTnG.cog) {
		contents += boxPanels(i)
	}
  }
  myPanel.vGap = 10
  myPanel.hGap = 10
  
  contents = myPanel
  visible = true
	
	def updateTables = {
    var i = 0
    var j = 1
    for(table <- tables) {
    	table.update(0, 0, "Name")
    	table.update(0, 1, "Points")
    	table.update(0, 2, "Goals")
    	table.update(0, 3, "Goals")
      	for(team <- controller.groups(i).getEasyTable.reverse) {
      		table.update(j, 0, team.name)
      		table.update(j, 1, team.points)
      		table.update(j, 2, team.goals)
      		table.update(j, 3, team.goalsAgainst)
      		j = j+1
      	}
      	j = 1
      	i = i+1
    }
  }
	
  def getGroupComponents(i: Int): List[Component] = {	  	
    controller.getGames(controller.groups(i)).map(x =>
	  		new FlowPanel {
	  		  contents += new Label(x.game._1.name)
	  		  contents += new Label(" - ")
	  		  contents += new Label(x.game._2.name)
	  		  contents += new Label("   ")
	  		  val goals = new TextField("0", 3)
	  		  contents += goals
	  		  contents += new Label(" : ")
	  		  val goalsAgainst = new TextField("0", 3)
	  		  contents += goalsAgainst
	  		  contents += Button("Commit") {
	  		    println(i+1 + ".groupindex " + x.index + ".gameindex " + goals.text.toInt + ".goals goalsagainst." + goalsAgainst.text.toInt)
	  		    controller.setGameResult(i+1, x.index, goals.text.toInt, goalsAgainst.text.toInt)
	  		  }
	  		}
	  	).toList :::
	  	List(tables(i))	  	
  }
}